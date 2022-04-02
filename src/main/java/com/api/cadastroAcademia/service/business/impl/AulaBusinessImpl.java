package com.api.cadastroAcademia.service.business.impl;

import com.api.cadastroAcademia.model.Aula;
import com.api.cadastroAcademia.service.business.mapper.AulaMapper;
import com.api.cadastroAcademia.business.AulaBusiness;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("aulaBusinessImpl")
@Scope("singleton")
public class AulaBusinessImpl implements AulaBusiness {

    private @Autowired AulaMapper aulaMapper;

    @Override
    @Transactional
    public Optional<List<Aula>> buscaAulas() {
        val aulas = aulaMapper.buscaAulas();
        if (aulas == null || aulas.isEmpty())
            return Optional.empty();
        return Optional.of(aulas);
    }
}
