package com.invoice_acounting.repositery;

import com.invoice_acounting.modal.customer.CustomerModal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice_acounting.entity.customer.LocalCustomer;

@Repository
public interface CustomerRepo extends MongoRepository<LocalCustomer,String>{

    public Boolean existsByCustomerId(String id);

}
