package com.api.cadastroAcademia.model;

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
public class Plano implements Serializable {

    private static final long serialVersionUID = -614224981342834055L;

    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private boolean ativo;
    private TipoPlano tipo;
}
