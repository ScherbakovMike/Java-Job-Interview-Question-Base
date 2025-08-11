package com.mikescherbakov.jobinterviewbase.infrastructure.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.sql",
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager")
@Profile("!test-minimal")
public class DataSourcePostgresConfiguration {

  @Bean
  @ConfigurationProperties("spring.datasource.postgres")
  public DataSourceProperties postgresDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource postgresDataSource() {
    return postgresDataSourceProperties().initializeDataSourceBuilder().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(postgresDataSource())
        .packages("com.mikescherbakov.jobinterviewbase.model.sql")
        .persistenceUnit("postgres")
        .build();
  }

  @Bean
  public PlatformTransactionManager postgresTransactionManager(
      EntityManagerFactoryBuilder builder) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(postgresEntityManagerFactory(builder).getObject());
    return transactionManager;
  }
}
