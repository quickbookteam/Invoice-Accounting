package com.accounting.modal.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.accounting.entity.customer.BillAddr;
import com.accounting.entity.customer.PrimaryEmailAddr;
import com.accounting.entity.customer.PrimaryPhone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalCustomerModal {
	private String customerId;
	private PrimaryEmailAddrModal primaryEmailAddr;
	private String syncToken;
	private String domain;
	private String givenName;
	private String displayName;
	private boolean billWithParent;
	private String fullyQualifiedName;
	private String companyName;
	private String familyName;
	private boolean sparse;
	private PrimaryPhoneModal primaryPhone;
	private boolean active;
	private boolean job;
	private double balanceWithJobs;
	private BillAddrModal billAddr;
	private String preferredDeliveryMethod;
	private boolean taxable;
	private String printOnCheckName;
	private double balance;
	private Date createTime;
	private Date lastUpdatedTime;

}
