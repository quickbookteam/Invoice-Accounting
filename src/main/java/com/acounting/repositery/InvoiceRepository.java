package com.acounting.repositery;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.acounting.entity.invoice.LocalInvoice;

@Repository
public interface InvoiceRepository extends MongoRepository <LocalInvoice,String>{
}
