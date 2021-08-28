package com.api.cadastroAcademia.service.business.mapper;

import com.api.cadastroAcademia.model.Plano;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanoMapper {

    Plano buscaPlano(@Param("idPlano") Integer idPlano);

    List<Plano>buscaPlanos();

}
