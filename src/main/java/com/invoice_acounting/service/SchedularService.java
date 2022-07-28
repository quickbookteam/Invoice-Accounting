package com.invoice_acounting.service;





import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;

import com.invoice_acounting.modal.customer.LocalCustomerModal;

public interface SchedularService {
   
    public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

}
