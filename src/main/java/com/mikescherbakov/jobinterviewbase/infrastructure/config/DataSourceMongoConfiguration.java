package com.mikescherbakov.jobinterviewbase.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.nosql")
@Profile("!test-minimal")
public class DataSourceMongoConfiguration extends AbstractMongoClientConfiguration {

  @Bean
  @ConfigurationProperties("spring.data.mongodb")
  public MongoProperties mongoProperties() {
    return new MongoProperties();
  }

  @Override
  @NonNull
  protected String getDatabaseName() {
    return mongoProperties().getDatabase();
  }

  @Bean
  @Override
  @NonNull
  public MongoClient mongoClient() {
    return MongoClients.create(mongoProperties().getUri());
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), getDatabaseName());
  }

  @Getter
  @Setter
  public static class MongoProperties {
    private String uri = "mongodb://localhost:27017";
    private String database = "testdb";
  }
}
