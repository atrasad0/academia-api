package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.exception.ApiException;
import com.api.cadastroAcademia.model.ApiMessage;
import com.api.cadastroAcademia.service.business.mapper.AlunoMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndPoint REST para testar se existe uma conexao com a API.
 */
@Slf4j
@RestController
@RequestMapping(value="/api")
public class APIController {

    private @Autowired AlunoMapper alunoMapper;

    @GetMapping("/teste-conexao")
    public ResponseEntity<?> conexao() {
        try {
            alunoMapper.testeConexao();
            String mensagem = "API REST ON";
            return ResponseEntity.status(HttpStatus.OK).body(new ApiMessage(mensagem));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }

    }
}
