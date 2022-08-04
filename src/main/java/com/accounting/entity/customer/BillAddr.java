package com.accounting.entity.customer;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class BillAddr {

	private Long id;
	private String city ;
	private String line1 ;
	private String postalCode ;
	private String lat ;
	private String _long;
	private String countrySubDivisionCode;
	
}
