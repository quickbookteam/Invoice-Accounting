package com.accounting.modal.invoice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.entity.invoice.CustomerRef;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceModal {
	
    private String invoiceId;
    private ArrayList<LineModal> line;
    private CustomerRef customerRef;

}
