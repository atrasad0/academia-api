package com.api.cadastroAcademia.service.business.impl;

import com.api.cadastroAcademia.business.AlunoBusiness;
import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.Aula;
import com.api.cadastroAcademia.model.Telefone;
import com.api.cadastroAcademia.model.dto.AlunoTO;
import com.api.cadastroAcademia.service.business.mapper.AlunoMapper;
import com.api.cadastroAcademia.service.business.mapper.AulaMapper;
import com.api.cadastroAcademia.service.business.mapper.TelefoneMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("alunoBusinessImpl")
@Scope("singleton")
@Slf4j
public class AlunoBusinessImpl implements AlunoBusiness {

    private @Autowired AlunoMapper alunoMapper;
    private @Autowired TelefoneMapper telefoneMapper;
    private @Autowired AulaMapper aulaMapper;

    @Override
    @Transactional
    public AlunoTO salvaAluno(@NonNull Aluno aluno) {
        validaParametros(aluno);
        injectDefaultValues(aluno);

        if (aluno.getId() == null) {
            isCpfDuplicado(aluno.getCpf());
            alunoMapper.insere(aluno);
        }
        else
            alunoMapper.modifica(aluno);

        salvaRelacaoAulasAluno(aluno.getAulas(), aluno.getId());
        salvaTelefones(aluno.getTelefones(), aluno);

        log.info(String.format("Method 'salvaAluno' executed and a %s has created with id: %s.", aluno.getClass().getSimpleName(), aluno.getId()));

        return new AlunoTO(aluno);
    }

    /**
     * Salva uma relação entre  o aluno e todas as aulas que o mesmo está matriculado.
     * @param listaAulas As aulas em que um aluno pode estar matriculado
     * @param idAluno O id do aluno.
     */
    private void salvaRelacaoAulasAluno(@NonNull List<Aula> listaAulas, int idAluno) {
        aulaMapper.removeAllAulaAluno(idAluno);

        for (val aula : listaAulas) {
            if (aula.getId() == null)
                aula.setId(aulaMapper.buscaIdAulaPorTipo(aula.getTipo().name()));
            aulaMapper.insereAulaAluno(aula.getId(), idAluno);
        }
    }

    /**
     * Salva todos os numeros de telefones de um aluno.
     * @param listTelefones A lista com todos os telefones.
     * @param aluno O "dono" desses numeros.
     */
    private void salvaTelefones (List<Telefone> listTelefones, @NonNull Aluno aluno) {
        log.info("Saving phones");

        telefoneMapper.removeAllNumbers(aluno.getId());
        val telefones = Objects.requireNonNullElse(listTelefones, new ArrayList<Telefone>());

        log.debug("phones {}", telefones);

        for (val tel : telefones) {
            //se o "dono" do telefone não for informado na requisição.
            if (tel.getAluno() == null)
                tel.setAluno(aluno);
            telefoneMapper.insere(tel);
        }
    }

    /**
     * Remove os caracteres especiaias de possiveis máscaras aplicadas a um cpf.
     * @param cpf O cpf que será processado.
     * @return O cpf com apenas valores numéricos.
     */
    private String setOnlyNumbers(String cpf) {
        return cpf.replaceAll("[\\D]", "");
    }

    @Override
    @Transactional
    public Optional<AlunoTO> buscaAluno (@NonNull Integer id) {
        val aluno = alunoMapper.buscaAluno(id);
        if(aluno == null)
            return Optional.empty();
        return Optional.of(new AlunoTO(aluno));
    }

    @Override
    public List<AlunoTO> buscaAlunos() {
        val alunos = alunoMapper.buscaAlunos();
        if(alunos == null || alunos.isEmpty())
            return Collections.emptyList();

        log.debug("{} found", alunos.size());

        return alunos.stream().map(AlunoTO::new).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void removeAluno(@NonNull Integer id) {
        alunoMapper.remove(id);
        log.info("Aluno removed, id: " + id);
    }

    /**
     * Verifica se um cpf informado já esta cadastrado no sistema.
     * @param cpf O cpf a ser verificado.
     * @throws IllegalArgumentException Se ja estiver cadastrado.
     */
    private void isCpfDuplicado(String cpf) {
        if (alunoMapper.isCpfDuplicado(cpf))
            throw new IllegalArgumentException("AlunoBusinessImpl.isCpfDuplicado - Este CPF já está cadastrado no sistema.");
    }

    /**
     * Valida todos os atributos necessários para criar um novo {@link Aluno}
     * @param aluno O aluno a ser salvo.
     * @throws IllegalArgumentException Se algum parametro estiver faltando na requisição.
     */
    private void validaParametros(Aluno aluno) {
        val sb = new StringBuilder();

        if (aluno.getNome() == null)
            sb.append("Nome do aluno e obrigatorio.\n");

        if (aluno.getCpf() == null)
            sb.append("CPF do aluno e obrigatorio.\n");

        if (aluno.getDataPagamento() == null)
            sb.append("Data de pagamento e obrigatorio.\n");

        if (aluno.getAulas() == null || aluno.getAulas().isEmpty())
            sb.append("Um aluno precisa estar matriculado em uma aula.\n");

        if (aluno.getPlano() == null) {
            sb.append("Plano do aluno e obrigatorio.\n");
        }

        if (sb.length() >0)
            throw new IllegalArgumentException("AlunoBusinessImpl.validaParametros - \n" + sb);
    }

    /**
     * Injeta valores no objeto {@link Aluno} que não foram explicitamente inseridos.
     * @param aluno O objeto a ser processado.
     */
    private void injectDefaultValues(Aluno aluno) {

        if (aluno.getDataCadastro() == null)
            aluno.setDataCadastro(LocalDate.now());

        if (aluno.getTelefones() == null || aluno.getTelefones().isEmpty())
            aluno.setTelefones(new ArrayList<>());

        aluno.setCpf(setOnlyNumbers(aluno.getCpf()));
    }

}
