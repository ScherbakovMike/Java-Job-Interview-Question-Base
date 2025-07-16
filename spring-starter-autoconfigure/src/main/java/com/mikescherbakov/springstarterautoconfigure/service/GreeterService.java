package com.mikescherbakov.springstarterautoconfigure.service;

public class GreeterService {
  private final String salutation;
  private final String userName;
  private final String suffix;

  public GreeterService(String salutation, String userName, String suffix) {
    this.salutation = salutation;
    this.userName = userName;
    this.suffix = suffix;
  }

  public String greet() {
    return String.format("%s, %s%s", salutation, userName, suffix);
  }
}
