package com.api.cadastroAcademia.model.dto.aluno;

import com.api.cadastroAcademia.model.enums.TipoPlano;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanoTO implements Serializable {
    private static final long serialVersionUID = 3926165228685255047L;

    private int id;
    private String descricao;
    private BigDecimal valor;
    private boolean ativo;
    private TipoPlano tipo;
}
