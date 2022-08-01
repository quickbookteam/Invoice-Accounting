package com.accounting.repositery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accounting.entity.Connection;

@Repository
@Qualifier("connectionRepository")
public  interface ConnectionRepositery extends MongoRepository<Connection, Long> {

}
