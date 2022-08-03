package com.accounting.util;

import java.util.Arrays;
import java.util.List;

import com.intuit.oauth2.config.Environment;

public   interface UtilConstants {

    public static String clientId="ABU0pV6Wc9PWCs3UhP0hsqvUyoA9vopY3yjT8oTmNrgggizNs5";
    public static String clientSecret= "KIMAYJDdSuGO3Sgc9Y7phwUH1kD9dsKLDvl6kXvQ";
    public static String refreshToken= "AB11668160062ME4JWITBrGuICCQHzoZNXI8EW9BLbPyrvjg1F";
    public static String RealmID = "4620816365236374530";
    public static Environment quickBooksEnvironment = Environment.SANDBOX;
    public static String quickBooksConnectURL = "https://sandbox-quickbooks.api.intuit.com/v3/company";

    public static String CUSTOMER_NOT_FOUND="Customer Not Found";
    public static String CUSTOMER_SAVED="User Saved Successfully";
    public static String CUSTOMER_DELETED="User Deleted Successfully";
    public static String CUSTOMER_LIST= "Getting User List  Successfull";
    public static String CUSTOMER_UPDATED="User Updated Successfully";
    public static String CUSTOMER_FOUND="Customer Found Successfully";

    public static String CONNECTION_STABLISHED="CONNECTION ESTABLISHED";
    public static String CONNECTION_NOT_FOUND="Connection Not Found";

    public static String INVOICE_NOT_FOUND="Invoice Not Found";
    public static String INVOICE_SAVED="Invoice Saved Successfully";
    public static String INVOICE_FOUND="Invoice found Successsfully";

    public static String TRANSECTION_SAVED="Transection Saved SuccessFully";
    public static String TRANSECTION_FOUND="Transection Found Successfully";
    public static String TRANSACTION_NOT_FOUND="Transaction Not Found";

    public static String FILE_NOT_FOUND="File Not Found";

    public static  List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

}
