package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telefone implements Serializable {

    private static final long serialVersionUID = 2218440734992803890L;

    private Integer id;
    private Integer ddi;
    private Integer ddd;
    private String  numero;
    private Aluno aluno;


}
