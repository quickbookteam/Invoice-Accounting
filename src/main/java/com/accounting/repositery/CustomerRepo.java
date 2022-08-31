package com.accounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.accounting.entity.customer.LocalCustomer;

@Repository
public interface CustomerRepo extends MongoRepository<LocalCustomer,String>,QueryByExampleExecutor<LocalCustomer>{

    public LocalCustomer existsByCustomerId(String id);
    public LocalCustomer findByCustomerId(String id);

}
