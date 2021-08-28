package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Telefone implements Serializable {

    private static final long serialVersionUID = 2218440734992803890L;

    private Integer id;
    private Integer ddi;
    private Integer ddd;
    private String  numero;
    private Aluno aluno;


}
