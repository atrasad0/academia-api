package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.business.AlunoBusiness;
import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * EndPoint REST que atende as requisiçoes relacionadas a um aluno.
 */
@Slf4j
@RestController
@RequestMapping(value="/api/aluno")
public class AlunoController {

    @Autowired private AlunoBusiness alunoBusiness;

    @PostMapping("/salva")
    public ResponseEntity<?> salva (@Valid @RequestBody Aluno aluno) {
        log.info("Post Aluno");
        log.debug("parameters {}" ,aluno);

       val entity = alunoBusiness.salvaAluno(aluno);

       if (entity.getRequestStatus().hasError())
           return new ResponseEntity<>(new ApiMessage(entity.getRequestStatus().getMessageError()), HttpStatus.INTERNAL_SERVER_ERROR);

       if (entity.getRequestStatus().isCreated())
           return new ResponseEntity<>(new ApiMessage("id:" + entity.getRequestStatus().getId()), HttpStatus.CREATED);

       return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);

    }

    @GetMapping("/{id}") ResponseEntity<?> aluno(@PathVariable("id") Integer id) {
        log.info("Get Aluno");
        log.debug("id {}", id);

        val response = alunoBusiness.buscaAluno(id);

        if (response.isEmpty())
            return new ResponseEntity<>(new ApiMessage("Aluno não encontrado"),HttpStatus.NOT_FOUND);


        if(response.get().getRequestStatus().hasError()) {
            return new ResponseEntity<>(new ApiMessage(response.get().getRequestStatus().getMessageError()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response.map( v -> new ResponseEntity<>(response.get().getAluno(),HttpStatus.FOUND))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/alunos") ResponseEntity<?> alunos() {
        log.info("Get alunos");

        val response = alunoBusiness.buscaAlunos();

        if (response.isEmpty())
            return new ResponseEntity<>( new ApiMessage("Tente realizar a requisicao novamente"), HttpStatus.SERVICE_UNAVAILABLE);

        if (response.get().isEmpty())  {
            return new ResponseEntity<>(new ApiMessage("Recursos não encontrados"), HttpStatus.NOT_FOUND);
        }

        return response.map( v -> new ResponseEntity<>(response.get(), HttpStatus.FOUND))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edita") ResponseEntity<?> edita(@Valid @RequestBody Aluno aluno) {
        log.info("put Aluno");
        log.debug("parameters {}", aluno);

        val entity = alunoBusiness.salvaAluno(aluno);

        if (entity.getRequestStatus().hasError())
            return new ResponseEntity<>(new ApiMessage(entity.getRequestStatus().getMessageError()), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/remove/{id}") ResponseEntity<?> remove(@PathVariable Integer id) {
        log.info("Delete Aluno");
        log.debug("id {}", id);

        val entity = alunoBusiness.buscaAluno(id);

        if (entity.isEmpty())
            return new ResponseEntity<>(new ApiMessage("Aluno não encontrado"), HttpStatus.NOT_FOUND);

        if (entity.get().getRequestStatus().hasError())
            return new ResponseEntity<>(new ApiMessage(entity.get().getRequestStatus().getMessageError()), HttpStatus.INTERNAL_SERVER_ERROR);

        val response = alunoBusiness.removeAluno(id);

        if (response.getRequestStatus().hasError())
            return new ResponseEntity<>(new ApiMessage(response.getRequestStatus().getMessageError()), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(new ApiMessage("Aluno deletado com sucesso"), HttpStatus.OK);
    }
}
