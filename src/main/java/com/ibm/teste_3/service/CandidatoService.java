package com.ibm.teste_3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.teste_3.dto.CandidatoDTO;
import com.ibm.teste_3.excepetion.CandidatoConflictException;
import com.ibm.teste_3.excepetion.CandidatoNotFoundException;
import com.ibm.teste_3.model.Candidato;
import com.ibm.teste_3.model.StatusCandidato;
import com.ibm.teste_3.repository.CandidatoRepository;

@Service
public class CandidatoService {
  @Autowired
  private CandidatoRepository repository;

  private Candidato buscarCandidatoPorNome(String nome) {
    List<Candidato> candidatos = repository.findAll();
    for (Candidato candidato : candidatos) {
      if (candidato.getNome().equals(nome)) {
        return candidato;
      }
    }
    return null;
  }

  private Candidato buscarCandidatoPorCodigo(int codCandidato) {
    List<Candidato> candidatos = repository.findAll();
    for (Candidato candidato : candidatos) {
      if (candidato.getCodCandidato().intValue() == codCandidato) {
        return candidato;
      }
    }
    return null;
  }

  // /start
  public int iniciarProcesso(String nome) {
    Candidato jaParticipa = buscarCandidatoPorNome(nome);
    if (jaParticipa != null || nome.isBlank()) {
      throw new CandidatoConflictException("Candidato já participa do processo.");
    } else {
      CandidatoDTO candidatoDTO = new CandidatoDTO(nome, StatusCandidato.Recebido);
      int idNumber = repository.save(new Candidato(candidatoDTO)).getCodCandidato().intValue();
      return idNumber;
    }
  }

  // /schedule
  public void marcarEntrevista(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null && candidato.getStatus() == StatusCandidato.Recebido) {
      repository.findById((long) codCandidato).map(c -> {
        c.setStatus(StatusCandidato.Qualificado);
        return repository.save(c);
      });
    } else {
      throw new CandidatoNotFoundException("Candidato não encontrado");
    }
  }

  // /disqualify
  public void desqualificarCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null) {
      repository.deleteById((long) codCandidato);
    } else {
      throw new CandidatoNotFoundException("Candidato não encontrado");
    }
  }

  // /approve
  public void aprovarCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null && candidato.getStatus() == StatusCandidato.Qualificado) {
      repository.findById((long) codCandidato).map(c -> {
        c.setStatus(StatusCandidato.Aprovado);
        return repository.save(c);
      });
    } else {
      throw new CandidatoNotFoundException("Candidato não encontrado");
    }
  }

  // /candidate/:id
  public String verificarStatusCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null) {
      return candidato.getStatus().toString();
    } else {
      throw new CandidatoNotFoundException("Candidato não encontrado");
    }
  }

  // /approved
  public List<String> obterAprovados() {
    List<String> aprovados = new ArrayList<>();
    List<Candidato> candidatos = repository.findApprovedList();
    candidatos.forEach(candidato -> {
      aprovados.add(candidato.getNome());
    });
    return aprovados;
  }

  public List<Candidato> test() {
    return repository.findAll();
  }
}
