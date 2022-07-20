package com.invoice_acounting.scheduler;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.dao.CustomerDao;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@EnableScheduling
@Configuration
public class CustomerScheduler {
    @Autowired
    Helper helper;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerService customerService;

//    @Scheduled(cron = "0 * * ? * *")//after every minute
   public Customer saveCustomerToQuickBookServer() throws FMSException {
       System.out.println(new Date());

        List<LocalCustomer> customers=customerDao.getCustomers_With_CreatedStatus();
        for(LocalCustomer customer1:customers)
        {
           CustomerModal customerModal= customerService.findById(customer1.get_id());
            Customer customer=customerService.saveCustomerToQuickBook(customerModal);
            customerService.saveId(customer.getId(),customer1.get_id());
        }
       return null;
   }
}
