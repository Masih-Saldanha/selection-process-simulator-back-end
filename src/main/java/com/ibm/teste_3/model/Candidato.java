package com.ibm.teste_3.model;

import com.ibm.teste_3.dto.CandidatoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidato {
  public Candidato(CandidatoDTO data) {
    // this.codCandidato = data.codCandidato();
    this.nome = data.nome();
    this.status = data.status();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // @Column(nullable = false)
  // private int codCandidato;

  // @Column(nullable = false)
  private String nome;

  // @Column(nullable = false)
  private StatusCandidato status;
}
