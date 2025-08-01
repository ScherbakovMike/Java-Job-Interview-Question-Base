package com.mikescherbakov.jobinterviewbase.application.service;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<String> handleException(RuntimeException exception) {
    return new ResponseEntity<>(
        "Some exception has happened in the app: " + exception, HttpStatus.BAD_REQUEST);
  }
}
