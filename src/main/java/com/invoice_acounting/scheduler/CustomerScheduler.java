//package com.invoice_acounting.scheduler;
//
//import java.util.Date;
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.intuit.ipp.data.Customer;
//import com.invoice_acounting.entity.customer.LocalCustomer;
//import com.invoice_acounting.modal.customer.LocalCustomerModal;
//import com.invoice_acounting.service.CustomerService;
//import com.invoice_acounting.service.SchedularService;
//import com.invoice_acounting.util.Helper;
//
//import lombok.extern.slf4j.Slf4j;
//
//@EnableScheduling
//@Configuration
//@Slf4j
//public class CustomerScheduler {
//
//	Helper helper;
//	ModelMapper modelMapper;
//
//	CustomerService customerService;
//
//	
//	SchedularService schedularService;
//
//	@Autowired
//	public CustomerScheduler(@Qualifier("customerServiceImplementation") CustomerService customerService,
//			@Qualifier("schedularServiceImplementation") SchedularService schedularService) {
//		this.helper = new Helper();
//		this.customerService = customerService;
//		this.schedularService=schedularService;
//		this.modelMapper = new ModelMapper();
//	}
//
//	@Autowired
//	ObjectMapper mapper;
//
//	@Scheduled(cron = "* * * ? * *") // after every minute
//	public Customer saveCustomerToQuickBookServer() throws Exception {
//		System.out.println(new Date());
//
//		List<LocalCustomer> customers = customerService.getCustomers_With_CreatedStatus();
//		for (LocalCustomer localCustomer : customers) {
//			LocalCustomerModal localCustomerModal = modelMapper.map(localCustomer, LocalCustomerModal.class);
//			Customer customer = schedularService.saveCustomerToQuickBook(localCustomerModal);
//			log.info("before save to quick book");
//			customerService.saveId(customer.getId(), localCustomer.get_id());
//			log.info("after save to quickbook");
//		}
//		return null;
//	}
////	
////	@Scheduled(cron = "0 * * ? * *") // after every minute
////	public Customer updateCustomerToQuickBookServer() throws FMSException {
////		System.out.println("update"+new Date());
////       String []syncToken= {"1","0"};
////		List<LocalCustomer> customers = customerDao.getCustomers_With_UpdatedStatus();
////		for (LocalCustomer item : customers) {
////			Random r=new Random();        
////	      	int randomNumber=r.nextInt(syncToken.length);
////			String randomElement = syncToken[randomNumber];
////			CustomerModal customerModal = mapper.convertValue(item, CustomerModal.class);
////			customerModal.setId(item.getCustomerId());
////			Customer customer = mapper.convertValue(customerModal, Customer.class);
//////			customer.setSyncToken(randomElement);
////			 customer = customerService.updateCustomerToQuickBook(customer);
////			customerService.updateStatus(customer.getId());
////			
////		}
////		return null;
////	}
//}
