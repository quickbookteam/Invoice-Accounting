package com.invoice_acounting.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemLineDetail{
    @JsonProperty("ItemRef") 
    public ItemRef itemRef;
    
}
