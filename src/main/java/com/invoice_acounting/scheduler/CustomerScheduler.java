package com.invoice_acounting.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.util.Helper;

@EnableScheduling
@Configuration
public class CustomerScheduler {

	Helper helper;

	private final CustomerService customerService;

	public CustomerScheduler(CustomerService customerService) {
		this.helper = new Helper();
		this.customerService = customerService;
	}

	@Autowired
	ObjectMapper mapper;

//	@Scheduled(cron = "0 * * ? * *") // after every minute
//	public Customer saveCustomerToQuickBookServer() throws FMSException {
//		System.out.println(new Date());
//
//		List<LocalCustomer> customers = customerService.getCustomers_With_CreatedStatus();
//		for (LocalCustomer customer1 : customers) {
//			LocalCustomerModal customerModal = customerService.findById(customer1.get_id());
//			Customer customer = customerService.saveCustomerToQuickBook(customerModal);
//			customerService.saveId(customer.getId(), customer1.get_id());
//		}
//		return null;
//	}
//	
//	@Scheduled(cron = "0 * * ? * *") // after every minute
//	public Customer updateCustomerToQuickBookServer() throws FMSException {
//		System.out.println("update"+new Date());
//       String []syncToken= {"1","0"};
//		List<LocalCustomer> customers = customerDao.getCustomers_With_UpdatedStatus();
//		for (LocalCustomer item : customers) {
//			Random r=new Random();        
//	      	int randomNumber=r.nextInt(syncToken.length);
//			String randomElement = syncToken[randomNumber];
//			CustomerModal customerModal = mapper.convertValue(item, CustomerModal.class);
//			customerModal.setId(item.getCustomerId());
//			Customer customer = mapper.convertValue(customerModal, Customer.class);
////			customer.setSyncToken(randomElement);
//			 customer = customerService.updateCustomerToQuickBook(customer);
//			customerService.updateStatus(customer.getId());
//			
//		}
//		return null;
//	}
}
