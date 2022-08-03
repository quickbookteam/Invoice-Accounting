package com.accounting.connection;

import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;


public interface Connections {
	public DataService createConnection() throws FMSException;
}
