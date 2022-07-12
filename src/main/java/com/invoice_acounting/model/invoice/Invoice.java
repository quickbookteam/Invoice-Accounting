package com.invoice_acounting.model.invoice;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intuit.ipp.core.IEntity;

public class Invoice implements IEntity {
	@JsonProperty("Line")
	public ArrayList<Line> line;
	@JsonProperty("CustomerRef")
	public CustomerRef customerRef;
	

}
