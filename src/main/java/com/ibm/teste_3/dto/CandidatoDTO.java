package com.ibm.teste_3.dto;

import com.ibm.teste_3.model.StatusCandidato;

public record CandidatoDTO(
    String nome,
    StatusCandidato status) {

}
