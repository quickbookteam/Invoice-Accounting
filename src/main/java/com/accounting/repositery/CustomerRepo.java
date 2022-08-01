package com.accounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.modal.customer.LocalCustomerModal;

@Repository
public interface CustomerRepo extends MongoRepository<LocalCustomer,String>{

    public LocalCustomer existsByCustomerId(String id);
    public LocalCustomer findByCustomerId(String id);

}
