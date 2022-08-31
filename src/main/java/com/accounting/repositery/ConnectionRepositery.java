package com.accounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accounting.entity.Connection;

@Repository
public  interface ConnectionRepositery extends MongoRepository<Connection, Long> {

}
