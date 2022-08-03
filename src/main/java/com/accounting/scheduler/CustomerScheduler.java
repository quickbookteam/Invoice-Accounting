package com.accounting.scheduler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.exception.CustomException;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.service.CustomerService;
import com.accounting.service.SchedularService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class CustomerScheduler {

	private ModelMapper modelMapper;

	private CustomerService customerService;

	private SchedularService schedularService;

	@Autowired
	public CustomerScheduler(CustomerService customerService, SchedularService schedularService) {

		this.customerService = customerService;
		this.schedularService = schedularService;
		this.modelMapper = new ModelMapper();
	}

//	@Scheduled(cron = "* * * ? * *") // after every second
	public void saveCustomerToQuickBookServer() {
		List<LocalCustomer> customers = customerService.getCustomersWithCreatedStatus();
		for (LocalCustomer localCustomer : customers) {
			LocalCustomerModal localCustomerModal = modelMapper.map(localCustomer, LocalCustomerModal.class);
			Customer customer;
			try {
				customer = schedularService.saveCustomerToQuickBook(localCustomerModal);
				log.info("Customer before save to quick book");
				customerService.saveCustomerId(customer.getId(), localCustomer.get_id());
				log.info("Customer after save to quickbook");
			} catch (Exception ex) {
				log.info(ex.getMessage());
			}

		}

	}
//	
//	@Scheduled(cron = "0 * * ? * *") // after every minute
//	public Customer updateCustomerToQuickBookServer() throws FMSException {
//		
//       String []syncToken= {"1","0"};
//		List<LocalCustomer> customers = customerDao.getCustomersWithUpdatedStatus();
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