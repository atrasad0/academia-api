package com.api.cadastroAcademia.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoPlano {
    MENSAL("Mensal"),
    TRIMESTRAL("Trimestral"),
    SEMESTRAL("Semestral"),
    ANUAL("Anual");

    private final String desc;


    public static TipoPlano getTipo(Integer tipo) {
        if (tipo == null) return null;

        return Arrays.stream(TipoPlano.values())
                .filter(v -> v.ordinal() == tipo)
                .findFirst()
                .orElse(null);
    }

    public static Integer getValue (Integer tipo) {
        return TipoPlano.getTipo(tipo).ordinal();
    }

}
