package com.invoice_acounting.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesItemLineDetail {
	@JsonProperty("ItemRef")
	public ItemRef itemRef;
}
