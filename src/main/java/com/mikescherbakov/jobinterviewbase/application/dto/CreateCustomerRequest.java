package com.mikescherbakov.jobinterviewbase.application.dto;

import java.time.Instant;
import java.util.UUID;

public record CreateCustomerRequest(UUID id, String name, Instant registrationDateTime) {}
