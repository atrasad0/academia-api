package com.api.cadastroAcademia.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoAula {
    DANCA("Dança"),
    LUTA("Luta"),
    GINASTICA("Ginástica"),
    MUSCULACAO("Musculação");

    private final String desc;

    public static TipoAula getTipo(Integer tipo) {
        if (tipo == null) return null;

        return Arrays.stream(TipoAula.values())
                .filter(v -> v.ordinal() == tipo)
                .findFirst()
                .orElse(null);

    }

    public static Integer getValue (Integer tipo) {
        return TipoAula.getTipo(tipo).ordinal();
    }


}
