package com.mikescherbakov.jobinterviewbase.model.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record NoSqlEntity(@Id String id, String name) {}
