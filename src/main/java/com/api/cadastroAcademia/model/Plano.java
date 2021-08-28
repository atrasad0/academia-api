package com.api.cadastroAcademia.model;

import com.api.cadastroAcademia.model.enums.TipoPlano;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plano implements Serializable {

    private static final long serialVersionUID = -614224981342834055L;

    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private Boolean ativo;
    private TipoPlano tipo;
}
