package com.accounting.util;

import java.util.Arrays;
import java.util.List;

import com.intuit.oauth2.config.Environment;

public   interface UtilConstants {
	
public static String clientId="ABU0pV6Wc9PWCs3UhP0hsqvUyoA9vopY3yjT8oTmNrgggizNs5";
public static String clientSecret= "KIMAYJDdSuGO3Sgc9Y7phwUH1kD9dsKLDvl6kXvQ";
public static String refreshToken= "AB11669546391wHJpWlSKUZO113ZHbxJIm1uFT5cxNO2DcqsP6";
public static String RealmID = "4620816365236374530";
public static Environment quickBooksEnvironment = Environment.SANDBOX;
public static String quickBooksConnectURL = "https://sandbox-quickbooks.api.intuit.com/v3/company";

public static String CONNECTION_ESTABLISHED ="Connection Established";
public static String CONNECTION_NOT_FOUND="Not yet connected to quick book server";

public static String CUSTOMER_NOT_FOUND="Customer Not Found";
public static String CUSTOMER_SAVED="User Saved Successfully";
public static String CUSTOMER_DELETED="User Deleted Successfully";
public static String CUSTOMER_LIST= "Getting User List  Successfull";
public static String CUSTOMER_UPDATED="User Updated Successfully";
public static String CUSTOMER_FOUND="Customer Found Successfully";
public static String CUSTOMER_ADD_FAILED="CUSTOMER add failed to quick book server";

public static String INVOICE_NOT_FOUND="Invoice Not Found";
public static String INVOICE_SAVED="Invoice Saved Successfully";
public static String INVOICE_FOUND="Invoice found Successsfully";
public static String INVOICE_ADD_FAILED = "Invoice Adding to QuickBook Failed ";

public static String TRANSECTION_SAVED="Transection Saved SuccessFully";
public static String TRANSECTION_FOUND="Transection Found Successfully";

public static String FILE_NOT_FOUND="File Not Found";
public static String Email="email";
public static String Customer="customer";
public static String Invoice="invoice";
public static String CRON="0 * * ? * *";
public static String Scheduler_failed="Add scheduler email or customer or invoice";

public static String INVALID_ADMIN = "Invalid user";


public static  List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");


}