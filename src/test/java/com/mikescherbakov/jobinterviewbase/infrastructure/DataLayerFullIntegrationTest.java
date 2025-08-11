package com.mikescherbakov.jobinterviewbase.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.nosql.NoSqlRepository;
import com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.sql.SqlRepository;
import com.mikescherbakov.jobinterviewbase.model.nosql.NoSqlEntity;
import com.mikescherbakov.jobinterviewbase.model.sql.SqlEntity;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
class DataLayerFullIntegrationTest {

  @Container
  static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:16")
          .withDatabaseName("testdb")
          .withUsername("test")
          .withPassword("test");

  @Container
  static MongoDBContainer mongoDBContainer =
      new MongoDBContainer("mongo:7.0.12").withExposedPorts(27017);

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.postgres.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.postgres.username", postgresContainer::getUsername);
    registry.add("spring.datasource.postgres.password", postgresContainer::getPassword);
    registry.add("spring.datasource.postgres.driver-class-name", () -> "org.postgresql.Driver");

    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    registry.add("spring.jpa.show-sql", () -> "true");

    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    registry.add("spring.data.mongodb.database", () -> "testdb");
  }

  @Autowired private SqlRepository sqlRepository;

  @Autowired private NoSqlRepository noSqlRepository;

  @BeforeEach
  void setUp() {
    noSqlRepository.deleteAll();
    sqlRepository.deleteAll();
  }

  @Test
  void shouldWriteAndReadFromH2Database() {
    SqlEntity entity = new SqlEntity(null, "Test SQL Entity");

    SqlEntity savedEntity = sqlRepository.save(entity);

    assertThat(savedEntity.getId()).isNotNull();
    assertThat(savedEntity.getName()).isEqualTo("Test SQL Entity");

    Optional<SqlEntity> foundEntity = sqlRepository.findById(savedEntity.getId());
    assertThat(foundEntity).isPresent();
    assertThat(foundEntity.get().getName()).isEqualTo("Test SQL Entity");

    List<SqlEntity> allEntities = sqlRepository.findAll();
    assertThat(allEntities).hasSize(1);
    assertThat(allEntities.get(0).getName()).isEqualTo("Test SQL Entity");
  }

  @Test
  void shouldWriteAndReadFromMongoDB() {
    NoSqlEntity entity = new NoSqlEntity(null, "Test NoSQL Entity");

    NoSqlEntity savedEntity = noSqlRepository.save(entity);

    assertThat(savedEntity.id()).isNotNull();
    assertThat(savedEntity.name()).isEqualTo("Test NoSQL Entity");

    Optional<NoSqlEntity> foundEntity = noSqlRepository.findById(savedEntity.id());
    assertThat(foundEntity).isPresent();
    assertThat(foundEntity.get().name()).isEqualTo("Test NoSQL Entity");

    List<NoSqlEntity> allEntities = noSqlRepository.findAll();
    assertThat(allEntities).hasSize(1);
    assertThat(allEntities.get(0).name()).isEqualTo("Test NoSQL Entity");
  }

  @Test
  void shouldWriteAndReadFromBothDatasources() {
    SqlEntity sqlEntity = new SqlEntity(null, "SQL Entity");
    NoSqlEntity noSqlEntity = new NoSqlEntity(null, "NoSQL Entity");

    SqlEntity savedSqlEntity = sqlRepository.save(sqlEntity);
    NoSqlEntity savedNoSqlEntity = noSqlRepository.save(noSqlEntity);

    assertThat(savedSqlEntity.getId()).isNotNull();
    assertThat(savedSqlEntity.getName()).isEqualTo("SQL Entity");
    assertThat(savedNoSqlEntity.id()).isNotNull();
    assertThat(savedNoSqlEntity.name()).isEqualTo("NoSQL Entity");

    List<SqlEntity> sqlEntities = sqlRepository.findAll();
    List<NoSqlEntity> noSqlEntities = noSqlRepository.findAll();

    assertThat(sqlEntities).hasSize(1);
    assertThat(noSqlEntities).hasSize(1);
    assertThat(sqlEntities.get(0).getName()).isEqualTo("SQL Entity");
    assertThat(noSqlEntities.get(0).name()).isEqualTo("NoSQL Entity");
  }
}
