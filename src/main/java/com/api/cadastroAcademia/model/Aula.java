package com.api.cadastroAcademia.model;

import com.api.cadastroAcademia.model.enums.TipoAula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aula implements Serializable {
    private static final long serialVersionUID = -1223336082003581861L;

    private Integer id;
    private TipoAula tipo;
    private transient List<Aluno> alunos;
}
