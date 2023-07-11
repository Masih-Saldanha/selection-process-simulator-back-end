package com.ibm.teste_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ibm.teste_3.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
  
}
