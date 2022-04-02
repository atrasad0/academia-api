package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessage {

    /** A mensagem a ser enviada pela API. */
    private String message = "";
}
