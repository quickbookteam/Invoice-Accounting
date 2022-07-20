package com.invoice_acounting.dao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.repositery.InvoiceRepository;
import com.invoice_acounting.util.Helper;

import com.invoice_acounting.util.QBOServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.data.mongodb.core.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceDao {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerRepo customerRepositery;

    @Autowired
    Helper helper;

    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseEntity<?> save(InvoiceModal invoiceModal){

        if( !customerRepositery.existsByCustomerId(invoiceModal.getCustomerRef().getValue())){
            return new ResponseEntity<>("Customer not valid",HttpStatus.BAD_REQUEST);
        }
       LocalInvoice invoice = helper.getMapper().map(invoiceModal, LocalInvoice.class);
       invoice.setInvoiceId("0");
       invoice.setStatus("created");
       LocalInvoice result= invoiceRepository.save(invoice);
       return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    public InvoiceModal findById(String id){
    	if(!invoiceRepository.existsById(id)) {
    		return null;
    	}
        LocalInvoice invoice=invoiceRepository.findById(id).get();
        return  helper.getMapper().map(invoice, InvoiceModal.class);
    }
    public List<LocalInvoice> findAll()
    {
        return invoiceRepository.findAll();
    }
    public Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException {
        DataService dataService=helper.getConnection();
        ObjectMapper mapper=new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Invoice invoice= mapper.convertValue(invoiceModal, Invoice.class);

        Invoice invoice1=dataService.add(invoice);
        return invoice1;
    }
    public void saveId(String id,String localInvoiceId)
    {
        LocalInvoice result=invoiceRepository.findById(localInvoiceId).get();
        result.setStatus("uploaded");
        result.setInvoiceId(id);
        invoiceRepository.save(result);
    }
    public List<LocalInvoice> getInvoices_With_Created_Status()
    {
        List<LocalInvoice> localInvoices=new ArrayList<>();
        Query query=new Query();
        query.addCriteria(Criteria.where("status").is("created"));
        localInvoices=mongoTemplate.find(query,LocalInvoice.class);
        System.out.println(localInvoices);
        return  localInvoices;
    }
}
