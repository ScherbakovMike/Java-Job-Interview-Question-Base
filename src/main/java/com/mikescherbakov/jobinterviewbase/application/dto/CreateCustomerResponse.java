package com.mikescherbakov.jobinterviewbase.application.dto;

import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import java.util.List;

public record CreateCustomerResponse(Customer customer, List<String> errors) {}
