package com.accounting.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.controller.CustomerController;
import com.accounting.exception.CustomFileNotFoundException;
import com.accounting.modal.customer.BillAddrModal;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.customer.PrimaryEmailAddrModal;
import com.accounting.modal.customer.PrimaryPhoneModal;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CSVHelper {
	public List<LocalCustomerModal> fileToCustomer(MultipartFile file) {
		BufferedReader br;
		List<LocalCustomerModal> customerModal = new ArrayList<LocalCustomerModal>();
		try {
			String line;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String data[];
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				customerModal.add(CSVHelper.customerIntializer(data));
			}
			br.close();
			return customerModal;

		} catch (IOException e) {

			log.info(e.getMessage());
			return null;
		}
	}

	public static LocalCustomerModal customerIntializer(String data[]) {
//		LocalCustomerModal customer = new LocalCustomerModal();
//		for (int i = 0; i < data.length; i++) {
			
//			customer.setActive(Boolean.parseBoolean(data[0]));
//			customer.setBalance(Double.parseDouble(data[1]));
//			customer.setBalanceWithJobs(Double.parseDouble(data[2]));
//			BillAddrModal billAddrModal = new BillAddrModal();
//			billAddrModal.setId(Long.parseLong(data[3]));
//			billAddrModal.setCity(data[4]);
//			billAddrModal.setCountrySubDivisionCode(data[5]);
//			billAddrModal.set_long(data[6]);
//			billAddrModal.setLat(data[7]);
//			billAddrModal.setLine1(data[8]);
//			billAddrModal.setPostalCode(data[9]);
//			customer.setBillAddr(billAddrModal);
//			customer.setBillWithParent(Boolean.parseBoolean(data[10]));
//			customer.setCompanyName(data[11]);
//			customer.setCreateTime(new Date());
//			customer.setDisplayName(data[12]);
//			customer.setDomain(data[13]);
//			customer.setFamilyName(data[14]);
//			customer.setFullyQualifiedName(data[15]);
//			customer.setGivenName(data[16]);
//			customer.setJob(Boolean.parseBoolean(data[17]));
//			customer.setLastUpdatedTime(new Date());
//			customer.setPreferredDeliveryMethod(data[18]);
//			PrimaryEmailAddrModal primaryEmailAddr = new PrimaryEmailAddrModal();
//			primaryEmailAddr.setAddress(data[19]);
//			customer.setPrimaryEmailAddr(primaryEmailAddr);
//			PrimaryPhoneModal PrimaryPhone = new PrimaryPhoneModal();
//			PrimaryPhone.setFreeFormNumber(data[20]);
//			customer.setPrimaryPhone(PrimaryPhone);
//			customer.setPrintOnCheckName(data[21]);
//			customer.setSparse(Boolean.parseBoolean(data[22]));
//			customer.setTaxable(Boolean.parseBoolean(data[23]));

//		}
		return ObjectConverter.customerModalToLocalCustomer(data);

	}

}