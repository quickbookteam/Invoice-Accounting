package com.accounting.util;

import java.util.Date;

import com.accounting.entity.customer.BillAddr;
import com.accounting.entity.customer.LocalCustomer;
import com.accounting.entity.customer.PrimaryEmailAddr;
import com.accounting.entity.customer.PrimaryPhone;
import com.accounting.modal.customer.BillAddrModal;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.customer.PrimaryEmailAddrModal;
import com.accounting.modal.customer.PrimaryPhoneModal;

public class ObjectConverter {

	public static LocalCustomerModal customerModalToLocalCustomer(String data[]) {

		LocalCustomerModal localCustomerModal = LocalCustomerModal.builder()
				.active(Boolean.parseBoolean(data[0])).balance(Double.parseDouble(data[1]))
				.balanceWithJobs(Double.parseDouble(data[2]))
				.billAddr(BillAddrModal.builder().id(Long.parseLong(data[3])).countrySubDivisionCode(data[5])
						._long(data[6]).city(data[4]).lat(data[7]).line1(data[8]).postalCode(data[9]).build())
				.billWithParent(Boolean.parseBoolean(data[10])).companyName(data[11]).createTime(new Date())
				.customerId("0").displayName(data[12]).domain(data[13]).familyName(data[14])
				.fullyQualifiedName(data[15]).givenName(data[16]).job(Boolean.parseBoolean(data[17]))
				.lastUpdatedTime(new Date()).preferredDeliveryMethod(data[18])
				.primaryEmailAddr(PrimaryEmailAddrModal.builder().address(data[19]).build())
				.primaryPhone(PrimaryPhoneModal.builder().freeFormNumber(data[20]).build()).printOnCheckName(data[21])
				.sparse(Boolean.parseBoolean(data[22])).taxable(Boolean.parseBoolean(data[23])).status("created").build();

		return localCustomerModal;

	}

}
