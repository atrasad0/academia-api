package com.api.cadastroAcademia.service.business.impl;

import com.api.cadastroAcademia.model.Plano;
import com.api.cadastroAcademia.service.business.mapper.PlanoMapper;
import com.api.cadastroAcademia.business.PlanoBusiness;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("planoBusinessImpl")
@Scope("singleton")
public class PlanoBusinessImpl implements PlanoBusiness {

    @Autowired private PlanoMapper planoMapper;

    @Override
    @Transactional
    public List<Plano> buscaPlanos() {
        val planos = planoMapper.buscaPlanos();
        if (planos == null || planos.isEmpty())
            return Collections.emptyList();
        return planos;
    }
}
