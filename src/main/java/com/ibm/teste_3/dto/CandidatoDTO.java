package com.ibm.teste_3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.teste_3.model.StatusCandidato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CandidatoDTO(
    // @NotEmpty int codCandidato,
    @NotEmpty @NotBlank String nome,
    @JsonIgnore StatusCandidato status) {

}
