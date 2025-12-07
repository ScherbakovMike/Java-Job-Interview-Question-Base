package com.mikescherbakov.jobinterviewbase.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Customer(UUID id, String name, Instant registrationDateTime) {}
