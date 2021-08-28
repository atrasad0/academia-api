package com.api.cadastroAcademia.service.business.mapper;

import com.api.cadastroAcademia.model.Telefone;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TelefoneMapper {

    void insere(Telefone telefone);

    List<Telefone> listaTelefonesPorIdAluno(Integer idAluno);

    void removeAllNumbers(Integer idAluno);
}
