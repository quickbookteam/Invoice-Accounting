package com.invoice_acounting.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line{
    @JsonProperty("DetailType") 
    public String detailType;
    @JsonProperty("Amount") 
    public double amount;
    @JsonProperty("SalesItemLineDetail") 
    public SalesItemLineDetail salesItemLineDetail;
}
