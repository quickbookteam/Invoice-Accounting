package com.invoice_acounting.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.invoice_acounting.modal.customer.BillAddrModal;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.PrimaryEmailAddrModal;
import com.invoice_acounting.modal.customer.PrimaryPhoneModal;

@Component
public class CSVHelper {
	public List<CustomerModal> fileToCustomer(MultipartFile file) {
		BufferedReader br;
		List<CustomerModal> customerModal=new ArrayList<CustomerModal>();
		try {
			String line;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String data[];
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				customerModal.add(CSVHelper.customerIntializer(data));
			}
			return customerModal;

		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static CustomerModal customerIntializer(String data[]) {
		CustomerModal customer = new CustomerModal();
		for (int i = 0; i < data.length; i++) {
			customer.setActive(Boolean.parseBoolean(data[0]));
			customer.setBalance(Double.parseDouble(data[1]));
			customer.setBalanceWithJobs(Double.parseDouble(data[2]));
			BillAddrModal billAddrModal=new BillAddrModal();
			billAddrModal.setId(Long.parseLong(data[3]));
			billAddrModal.setCity(data[4]);
			billAddrModal.setCountrySubDivisionCode(data[5]);
			billAddrModal.set_long(data[6]);
			billAddrModal.setLat(data[7]);
			billAddrModal.setLine1(data[8]);
			billAddrModal.setPostalCode(data[9]);
			customer.setBillAddr(billAddrModal);
			customer.setBillWithParent(Boolean.parseBoolean(data[10]));
			customer.setCompanyName(data[11]);
			customer.setCreateTime(new Date());
			customer.setDisplayName(data[12]);
			customer.setDomain(data[13]);
			customer.setFamilyName(data[14]);
			customer.setFullyQualifiedName(data[15]);
			customer.setGivenName(data[16]);
			customer.setJob(Boolean.parseBoolean(data[17]));
			customer.setLastUpdatedTime(new Date());
			customer.setPreferredDeliveryMethod(data[18]);
			PrimaryEmailAddrModal primaryEmailAddr=new PrimaryEmailAddrModal();
			primaryEmailAddr.setAddress(data[19]);
			customer.setPrimaryEmailAddr(primaryEmailAddr);
			PrimaryPhoneModal PrimaryPhone=new PrimaryPhoneModal();
			PrimaryPhone.setFreeFormNumber(data[20]);
			customer.setPrimaryPhone(PrimaryPhone);
			customer.setPrintOnCheckName(data[21]);
			customer.setSparse(Boolean.parseBoolean(data[22]));
			customer.setTaxable(Boolean.parseBoolean(data[23]));
			
		}
		return customer;

	}
	
	
	

	

}