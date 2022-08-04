package com.accounting.modal.customer;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModal {
	private String id;
	@Valid
	private PrimaryEmailAddrModal primaryEmailAddr;
	private String syncToken;
	@NotBlank
	private String domain;
	@NotBlank
	private String givenName;
	@NotBlank
	private String displayName;

	private boolean billWithParent;

	@NotNull

	private String fullyQualifiedName;

	private String companyName;

	private String familyName;
	private boolean sparse;

	private PrimaryPhoneModal primaryPhone;
	private boolean active;
	private boolean job;
	private double balanceWithJobs;
	@NotNull
	private BillAddrModal billAddr;
	private String preferredDeliveryMethod;
	private boolean taxable;
	private String printOnCheckName;

	@NotNull
	private double balance;
	private Date createTime;
	private Date lastUpdatedTime;
}
