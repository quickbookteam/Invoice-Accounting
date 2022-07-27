package com.invoice_acounting.modal.invoice;


import com.invoice_acounting.entity.invoice.CustomerRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import com.invoice_acounting.entity.customer.LocalCustomer;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceModal {
	
    private String invoiceId;
    private ArrayList<LineModal> line;
    private CustomerRef customerRef;

}
