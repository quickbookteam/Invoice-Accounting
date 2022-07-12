package com.invoice_acounting.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Line {
	@JsonProperty("DetailType")
	public String detailType;
	@JsonProperty("Amount")
	public double amount;
	@JsonProperty("SalesItemLineDetail")
	public SalesItemLineDetail salesItemLineDetail;
}
