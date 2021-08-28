package com.api.cadastroAcademia.service.business.mapper;

import com.api.cadastroAcademia.model.Aula;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AulaMapper {

    List<Aula> listaAulasPorIdAluno(@NonNull @Param("idAluno") Integer idAluno);

    @NonNull
    void insereAulaAluno(@Param("idAula")Integer idAula, @Param("idAluno") Integer idAluno);

    void removeAllAulaAluno(@Param("idAluno") Integer idAluno);

    Integer buscaIdAulaPorTipo(@Param("tipoAula")String tipoAula);

    List<Aula> buscaAulas();
}
