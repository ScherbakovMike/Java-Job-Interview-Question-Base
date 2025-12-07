package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity;

import static jakarta.persistence.GenerationType.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

@Profile(value = {"postgres-jpa", "postgres-manual"})
@Entity
@Table(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = UUID)
  UUID id;

  String name;

  Instant registrationDateTime;
}
