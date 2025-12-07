package com.mikescherbakov.jobinterviewbase.infrastructure.web.controller;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.application.service.*;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  ResponseEntity<CreateCustomerResponse> createCustomer(
      @RequestBody CreateCustomerRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
  }

  @GetMapping
  ResponseEntity<Page<Customer>> getCustomer(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers(pageable));
  }
}
