package com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.sql;

import com.mikescherbakov.jobinterviewbase.model.sql.SqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends JpaRepository<SqlEntity, Long> {}
