package com.invoice_acounting.repositery;


import com.invoice_acounting.entity.invoice.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository <Invoice,String>{
}
