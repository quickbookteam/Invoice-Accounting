package com.invoice_acounting.entity.customer;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

//@Entity
@Document(collection =  "customers")
@Data
public class LocalCustomer
{
	@Id
	private String  _id;
	private String customerId;
	private PrimaryEmailAddr primaryEmailAddr;
	private String domain;
	private String givenName;
	@Indexed(unique=true)
	private String displayName;
	private boolean billWithParent;
	private String fullyQualifiedName;
	private String companyName ;
	private String familyName ;
	private boolean sparse ;
	private PrimaryPhone primaryPhone;
	private boolean active;
	private boolean job; 
	private double balanceWithJobs;
	private BillAddr billAddr;
	private String preferredDeliveryMethod;
	private boolean taxable;
	private String printOnCheckName;
	private double balance;
	private Date createTime;
	private Date lastUpdatedTime;
	private String status;
    
}