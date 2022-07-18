package com.invoice_acounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.invoice_acounting.entity.customer.Customer;

@Repository
public interface CustomerRepo extends MongoRepository<Customer,String>{
	
}
