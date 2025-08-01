package com.mikescherbakov.jobinterviewbase.infrastructure.out;

import com.mikescherbakov.jobinterviewbase.domain.exception.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/failed-path")
public class BrokenController {

  @GetMapping
  @ResponseBody
  public String getAll() {
    throw new InControllerException("An exception within controller");
  }

  @GetMapping("/one")
  @ResponseBody
  public String getOne() {
    throw new InAppException("An exception within app");
  }

  @ExceptionHandler
  public ResponseEntity<String> handleException(InControllerException exception) {
    return new ResponseEntity<>(
        "Some exception has happened in the controller: " + exception, HttpStatus.BAD_REQUEST);
  }
}
