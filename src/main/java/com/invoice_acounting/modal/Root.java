package com.invoice_acounting.modal;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Root{
    @JsonProperty("Line") 
    public ArrayList<Line> line;
    @JsonProperty("CustomerRef") 
    public CustomerRef customerRef;
}
