package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.business.AulaBusiness;
import com.api.cadastroAcademia.business.PlanoBusiness;
import com.api.cadastroAcademia.exception.ApiException;
import com.api.cadastroAcademia.model.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndPoint REST que atende as requisiçoes relacionadas a uma academia.
 */

@Slf4j
@RestController
@RequestMapping(value="/academia")
public class AcademiaController {

    private @Autowired PlanoBusiness planoBusiness;
    private @Autowired AulaBusiness aulaBusiness;

    @GetMapping("/busca-planos") ResponseEntity<?> getPLanos() {
        try {
            val entity = planoBusiness.buscaPlanos();
            if (entity.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(new ApiMessage("Nenhum plano cadastrado."));

            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }

    @GetMapping("/busca-aulas") ResponseEntity<?> getAulas() {
        try {
            val entity = aulaBusiness.buscaAulas();
            if (entity.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessage("Nenhuma aula cadastrada."));

            return ResponseEntity.ok(entity.get());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

    }

}
