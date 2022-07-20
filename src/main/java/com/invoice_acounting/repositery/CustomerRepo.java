package com.invoice_acounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice_acounting.entity.customer.LocalCustomer;

@Repository
public interface CustomerRepo extends MongoRepository<LocalCustomer,String>{


}
