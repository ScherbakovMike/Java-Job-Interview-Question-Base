package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Profile(value = {"mongo-jpa", "mongo-manual"})
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDocument {

  @Id UUID id;

  String name;

  Instant registrationDateTime;
}
