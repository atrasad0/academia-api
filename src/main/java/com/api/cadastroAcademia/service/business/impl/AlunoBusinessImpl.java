package com.api.cadastroAcademia.service.business.impl;

import com.api.cadastroAcademia.business.AlunoBusiness;
import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.Aula;
import com.api.cadastroAcademia.model.Telefone;
import com.api.cadastroAcademia.model.dto.aluno.AlunoTO;
import com.api.cadastroAcademia.service.business.mapper.AlunoMapper;
import com.api.cadastroAcademia.service.business.mapper.AulaMapper;
import com.api.cadastroAcademia.service.business.mapper.TelefoneMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
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
@AllArgsConstructor
@Slf4j
public class AlunoBusinessImpl implements AlunoBusiness {

    private final AlunoMapper alunoMapper;
    private final TelefoneMapper telefoneMapper;
    private final AulaMapper aulaMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AlunoTO salvaAluno(@NonNull AlunoTO alunoTO) {
        val aluno = this.toAluno(alunoTO);

        this.validaParametros(aluno);
        this.injectDefaultValues(aluno);

        if (aluno.getId() == null || aluno.getId() == 0) {
            this.isCpfDuplicado(aluno.getCpf());
            alunoMapper.insere(aluno);
        }
        else
            alunoMapper.modifica(aluno);

        this.salvaRelacaoAulasAluno(aluno.getAulas(), aluno.getId());
        this.salvaTelefones(aluno.getTelefones(), aluno);

        log.info(String.format("Method 'salvaAluno' executed and a %s has created with id: %s.", aluno.getClass().getSimpleName(), aluno.getId()));
        return toAlunoDTO(aluno);
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
        return Optional.of(modelMapper.map(aluno, AlunoTO.class));
    }

    @Override
    public List<AlunoTO> buscaAlunos() {
        val alunos = alunoMapper.buscaAlunos();
        if(alunos == null || alunos.isEmpty())
            return Collections.emptyList();

        log.debug("{} found", alunos.size());

        return alunos.stream().map(this::toAlunoDTO).collect(Collectors.toList());
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

        if (Objects.isNull(aluno.getNome()))
            sb.append("Nome do aluno \u00E9 obrigat\u00F3rio.\n");
        else {
            if(aluno.getNome().isBlank() || aluno.getNome().length() < 3) {
                sb.append("Nome do aluno deve ter no m\u00EDnimo 3 caracteres");
            }
        }

        if (Objects.isNull(aluno.getCpf()))
            sb.append("CPF do aluno \u00E9 obrigat\u00F3rio.\n");

        if (Objects.isNull(aluno.getDataPagamento()))
            sb.append("Data de pagamento \u00E9 obrigat\u00F3rio.\n");

        if (Objects.isNull(aluno.getAulas()) || aluno.getAulas().isEmpty())
            sb.append("Um aluno precisa estar matriculado em uma aula.\n");

        if (Objects.isNull(aluno.getPlano())) {
            sb.append("Plano do aluno \u00E9 obrigat\u00F3rio..\n");
        }

        if (Objects.isNull(aluno.getDataCadastro())) {
            sb.append("Data de cadastro \u00E9 um campo obrigat\u00F3rio.\n");
        } else {
            if (aluno.getDataCadastro().isAfter(LocalDate.now())) {
                sb.append("Data de cadastro n\u00E3o pode estar no futuro.\n");
            }
        }

        if (sb.length() > 0)
            throw new IllegalArgumentException(sb.toString());
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

    /**
     * Converte um {@link Aluno} em um {@link AlunoTO}
     * @param aluno O aluno a ser tranformado.
     * @return O aluno no formato {@link AlunoTO}.
     */
    private AlunoTO toAlunoDTO(@NonNull Aluno aluno) {
        return modelMapper.map(aluno, AlunoTO.class);
    }

    /**
     * Converte um {@link AlunoTO} em um {@link Aluno}
     * @param alunoTO O aluno a ser tranformado.
     * @return O aluno no formato {@link Aluno}.
     */
    private Aluno toAluno(@NonNull AlunoTO alunoTO) {
        return modelMapper.map(alunoTO, Aluno.class);
    }

}
