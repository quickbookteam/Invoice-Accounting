package com.invoice_acounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.services.QueryResult;
import com.invoice_acounting.config.QuickBookIntegration;
import com.invoice_acounting.util.Helper;



@RestController
public class TestController {
	@Autowired
	QuickBookIntegration quickBookIntegration;

	QueryResult queryResult;

	@GetMapping("/test")
	public void test() {
//		DataService service = dataService;

		String sql = "select * from companyinfo";

		try {
			queryResult = quickBookIntegration.demo().executeQuery(sql);
			CompanyInfo companyInfo = (CompanyInfo) queryResult.getEntities().get(0);
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(companyInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
