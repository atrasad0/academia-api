package com.api.cadastroAcademia.model.dto.aluno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneTO implements Serializable {
    private static final long serialVersionUID = -6465184773555581174L;

    private Integer id;
    private Integer ddi;
    private Integer ddd;
    private String  numero;

}
