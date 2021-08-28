package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.model.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndPoint REST para testar se existe uma conexao com a API.
 */
@RestController
@RequestMapping(value="/api")
public class APIController {


    @GetMapping("/conexao")
    public ResponseEntity<ApiMessage> conexao() {
        String mensagem = "API REST ON";
        return new ResponseEntity<ApiMessage>(new ApiMessage(mensagem), HttpStatus.OK);
    }
}
