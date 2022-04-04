package com.api.cadastroAcademia.model.dto.aluno;

import com.api.cadastroAcademia.model.enums.TipoAula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaTO implements Serializable {
    private static final long serialVersionUID = 4185594716500834442L;

    private int id;
    private TipoAula tipo;
}
