package com.acounting.service;





import com.acounting.modal.customer.LocalCustomerModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;

public interface SchedularService {
   
    public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

}
