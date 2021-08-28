package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiMessage {

    /** A mensagem a ser enviada pela API. */
    private String message = "";
}
