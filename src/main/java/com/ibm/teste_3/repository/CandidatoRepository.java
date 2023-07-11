package com.ibm.teste_3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ibm.teste_3.dto.CandidatoDTO;
import com.ibm.teste_3.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
  @Query("SELECT c FROM Candidato c WHERE c.status = com.ibm.teste_3.model.StatusCandidato.Aprovado")
  List<Candidato> findApprovedList();
}
