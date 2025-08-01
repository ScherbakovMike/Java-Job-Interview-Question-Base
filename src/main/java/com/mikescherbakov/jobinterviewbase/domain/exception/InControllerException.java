package com.mikescherbakov.jobinterviewbase.domain.exception;

public class InControllerException extends RuntimeException {
  public InControllerException(String message) {
    super(message);
  }
}
