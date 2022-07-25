package com.invoice_acounting.repositery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice_acounting.entity.Connection;

@Repository
public  interface ConnectionRepositery extends MongoRepository<Connection, Long> {

}
