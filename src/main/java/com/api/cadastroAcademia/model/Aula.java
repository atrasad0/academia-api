package com.api.cadastroAcademia.model;

import com.api.cadastroAcademia.model.enums.TipoAula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aula implements Serializable {
    private static final long serialVersionUID = -1223336082003581861L;

    private Integer id;
    private TipoAula tipo;
    private transient List<Aluno> alunos;
}
