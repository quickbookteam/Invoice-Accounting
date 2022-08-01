package com.accounting.service;





import com.accounting.modal.customer.LocalCustomerModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;

public interface SchedularService {
   
    public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

}
