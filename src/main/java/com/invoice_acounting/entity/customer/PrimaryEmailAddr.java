package com.invoice_acounting.entity.customer;

import lombok.Data;

@Data
public class PrimaryEmailAddr {

	private String address ;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
