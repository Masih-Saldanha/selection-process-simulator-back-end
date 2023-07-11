package com.ibm.teste_3.excepetion;

public class CandidatoNotFoundException extends RuntimeException {
  public CandidatoNotFoundException(String message) {
    super(message);
  }
}
