package com.invoice_acounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.services.QueryResult;
import com.invoice_acounting.util.Helper;

import javax.mail.internet.MimeMessage;
import java.util.*;


@RestController
public class TestController {
//	@Autowired
//	Helper helper;
//
//	QueryResult queryResult;
//
//	@GetMapping("/test")
//	public Object test() {
////		DataService service = dataService;
//
//		String sql = "select * from companyinfo";
//
//		try {
//			queryResult =helper.getConnection().executeQuery(sql);
//			CompanyInfo companyInfo = (CompanyInfo) queryResult.getEntities().get(0);
//			ObjectMapper mapper = new ObjectMapper();
//			System.out.println(mapper.writeValueAsString(companyInfo));
//			return mapper.writeValueAsString(companyInfo);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//    String chartConfig = "{" +
//            "\"type\": \"bar\"," +
//            "\"data\": {" +
//            "\"labels\": [2012, 2013, 2014, 2015, 2016]," +
//            "\"datasets\": [{" +
//            "\"label\": \"Users\"," +
//            "\"data\": [120, 60, 50, 180, 120]" +
//            "}]" +
//            "}" +
//            "}";
//
//    String chartConfigTemplate = "{" +
//            "\"type\": \"bar\"," +
//            "\"data\": {" +
//            "\"labels\": [2012, 2013, 2014, 2015, 2016]," +
//            "\"datasets\": [{" +
//            "\"label\": \"Users\"," +
//            "\"data\": [ %DATA_VALUES% ]" +
//            "}]" +
//            "}" +
//            "}";

//    ArrayList<Integer> values = new ArrayList<Integer>();
//    List<Integer> val =new ArrayList<>();
//    values.add(60);
//    values.add(50);
//    values.add(180);
//    values.add(120);
//
//    String chartConfig =
//            chartConfigTemplate.replace("%DATA_VALUES%", values.toString());

}
