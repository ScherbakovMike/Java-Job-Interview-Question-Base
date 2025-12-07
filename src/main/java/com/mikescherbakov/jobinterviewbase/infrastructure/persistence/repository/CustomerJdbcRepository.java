package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.repository;

import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerRepositoryPort;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerEntity;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Profile("postgres-manual")
@Repository
@RequiredArgsConstructor
public class CustomerJdbcRepository implements CustomerRepositoryPort {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<CustomerEntity> rowMapper =
      (ResultSet rs, int rowNum) -> {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(UUID.fromString(rs.getString("id")));
        entity.setName(rs.getString("name"));
        Timestamp timestamp = rs.getTimestamp("registration_date_time");
        entity.setRegistrationDateTime(timestamp != null ? timestamp.toInstant() : null);
        return entity;
      };

  public CustomerEntity save(CustomerEntity entity) {
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID());
    }
    jdbcTemplate.update(
        "INSERT INTO customers (id, name, registration_date_time) VALUES (?, ?, ?)",
        entity.getId(),
        entity.getName(),
        entity.getRegistrationDateTime() != null
            ? Timestamp.from(entity.getRegistrationDateTime())
            : null);
    return entity;
  }

  public Page<CustomerEntity> findAll(Pageable pageable) {
    long total = count();

    String sql =
        "SELECT id, name, registration_date_time FROM customers ORDER BY id LIMIT ? OFFSET ?";
    List<CustomerEntity> content =
        jdbcTemplate.query(sql, rowMapper, pageable.getPageSize(), pageable.getOffset());

    return new PageImpl<>(content, pageable, total);
  }

  public long count() {
    Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customers", Long.class);
    return count != null ? count : 0;
  }

  @Transactional
  public void deleteByName(String name) {
    jdbcTemplate.update("DELETE FROM customers WHERE name = ?", name);
  }
}
