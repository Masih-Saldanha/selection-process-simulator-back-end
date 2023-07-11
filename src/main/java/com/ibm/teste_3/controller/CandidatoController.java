package com.ibm.teste_3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.teste_3.dto.CandidatoDTO;
import com.ibm.teste_3.dto.CodCandidatoRequestDTO;
import com.ibm.teste_3.model.Candidato;
import com.ibm.teste_3.service.CandidatoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
  @Autowired
  private CandidatoService service;

  @GetMapping
  public List<Candidato> test() {
    return service.test();
  }

  // /start
  @PostMapping("/start")
  public int start(@RequestBody @Valid CandidatoDTO req) {
    return service.iniciarProcesso(req.nome());
  }

  // /schedule
  @PostMapping("/schedule")
  public void schedule(@RequestBody CodCandidatoRequestDTO req) {
    service.marcarEntrevista(req.codCandidato());
  }

  // /disqualify
  @PostMapping("/disqualify")
  public void disqualify(@RequestBody CodCandidatoRequestDTO req) {
    service.desqualificarCandidato(req.codCandidato());
  }

  // /approve
  @PostMapping("/approve")
  public void approve(@RequestBody CodCandidatoRequestDTO req) {
    service.aprovarCandidato(req.codCandidato());
  }

  // /candidate/:id
  @GetMapping("/status/candidate/{codCandidato}")
  public String candidateStatusById(@PathVariable int codCandidato) {
    return service.verificarStatusCandidato(codCandidato);
  }

  // /approved
  @GetMapping("/approved")
  public List<String> approved() {
    return service.obterAprovados();
  }
}
