package com.api.cadastroAcademia.controller;

import com.api.cadastroAcademia.business.AulaBusiness;
import com.api.cadastroAcademia.business.PlanoBusiness;
import com.api.cadastroAcademia.model.ApiMessage;
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

@RestController
@RequestMapping(value="/api/academia")
public class AcademiaController {

    private @Autowired
    PlanoBusiness planoBusiness;
    private @Autowired
    AulaBusiness aulaBusiness;

    @GetMapping("/buscaPlanos") ResponseEntity<?> buscaPlanos() {
        val entity = planoBusiness.buscaPlanos();
        if (entity.isEmpty())
            return new ResponseEntity<>(new ApiMessage("Nenhum plano cadastrado."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity.get(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscaAulas") ResponseEntity<?> buscaAulas() {
        val entity = aulaBusiness.buscaAulas();
        if (entity.isEmpty())
            return new ResponseEntity<>(new ApiMessage("Nenhuma aula cadastrada."), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity.get(), HttpStatus.NOT_FOUND);
    }

}
