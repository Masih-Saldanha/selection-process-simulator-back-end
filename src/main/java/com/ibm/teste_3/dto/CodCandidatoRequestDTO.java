package com.ibm.teste_3.dto;

import jakarta.validation.constraints.NotEmpty;

public record CodCandidatoRequestDTO(@NotEmpty int codCandidato) {
  
}
