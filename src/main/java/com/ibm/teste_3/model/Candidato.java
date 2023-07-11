package com.ibm.teste_3.model;

import com.ibm.teste_3.dto.CandidatoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
    this.nome = data.nome();
    this.status = data.status();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidato_sequence")
  @SequenceGenerator(name = "candidato_sequence", sequenceName = "candidato_seq", allocationSize = 1, initialValue = 1000)
  private Long codCandidato;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private StatusCandidato status;
}
