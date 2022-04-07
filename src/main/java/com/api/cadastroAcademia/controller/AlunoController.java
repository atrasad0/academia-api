package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.business.AlunoBusiness;
import com.api.cadastroAcademia.exception.ApiException;
import com.api.cadastroAcademia.exception.ResourceNotFoundException;
import com.api.cadastroAcademia.model.ApiMessage;
import com.api.cadastroAcademia.model.Pagination;
import com.api.cadastroAcademia.model.dto.aluno.AlunoTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.session.RowBounds;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Objects;

/**
 * EndPoint REST que atende as requisiçoes relacionadas a um aluno.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value="/alunos")
public class AlunoController {

    private final AlunoBusiness alunoBusiness;

    @PostMapping
    public ResponseEntity<?> post (@Valid @RequestBody AlunoTO aluno) {
        log.info("Post Aluno");
        log.debug("parameters {}", aluno);

        try {
            val entity = alunoBusiness.salvaAluno(aluno);
            val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();

            return ResponseEntity.created(uri).body(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }

    @GetMapping("/{id}") ResponseEntity<?> get(@PathVariable("id") Integer id) {
        log.info("Get Aluno");
        log.debug("id {}", id);

        try {
            val entity = alunoBusiness.buscaAluno(id);
            if (entity.isEmpty())
                throw new ResourceNotFoundException(String.format("Aluno com o ID: %s não encontrado.", id));

            return entity.map( v ->  ResponseEntity.status(HttpStatus.FOUND).body(entity.get()))
                         .orElseThrow();

        } catch (ResourceNotFoundException re) {
            log.error(re.getMessage());
            throw new ResourceNotFoundException(re.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }

    @GetMapping
    ResponseEntity<?> list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size ) {
        log.info("Get alunos");

        try {
            Pagination pagination = null;

            if (Objects.nonNull(page) && Objects.nonNull(size)) {
                pagination = new Pagination(page, size);
            }

            val entities = alunoBusiness.buscaAlunos(pagination);

            if (entities.getContent().isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(new ApiMessage("Nenhum aluno cadastrado."));

            return ResponseEntity.status(HttpStatus.OK).body(entities);

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }

    @PutMapping
    ResponseEntity<?> edit(@Valid @RequestBody AlunoTO aluno) {
        log.info("put Aluno");
        log.debug("parameters {}", aluno);

        try {
            val entity = alunoBusiness.salvaAluno(aluno);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiMessage(String.format("Aluno %s editado com sucesso.", entity.getNome())));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Aluno");
        log.debug("id {}", id);

        try {
            val entity = alunoBusiness.buscaAluno(id);
            if (entity.isEmpty())
                throw new ResourceNotFoundException(String.format("Aluno com o ID: %s não encontrado.", id));

            alunoBusiness.removeAluno(id);
            return ResponseEntity.status(HttpStatus.OK).body( new ApiMessage(String.format("Aluno %s deletado com sucesso", entity.get().getNome())));

        } catch (ResourceNotFoundException re) {
            log.error(re.getMessage());
            throw new ResourceNotFoundException(re.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }
}
