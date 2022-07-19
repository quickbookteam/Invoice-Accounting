package com.invoice_acounting.modal.invoice;


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
	
    private String id;
    private ArrayList<LineModal> line;
    private LocalCustomer localCustomer;
    private String status;
}
