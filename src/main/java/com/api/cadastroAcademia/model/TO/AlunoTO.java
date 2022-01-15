package com.api.cadastroAcademia.model.TO;

import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.utils.RequestStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlunoTO  {
     private Aluno aluno;
     private RequestStatus requestStatus;



}
