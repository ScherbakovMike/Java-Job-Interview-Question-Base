package com.mikescherbakov.jobinterviewbase.infrastructure.adapter.out.nosql;

import com.mikescherbakov.jobinterviewbase.model.nosql.NoSqlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoSqlRepository extends MongoRepository<NoSqlEntity, String> {}
