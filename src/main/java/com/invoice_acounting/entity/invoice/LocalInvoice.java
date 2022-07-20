package com.invoice_acounting.entity.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.invoice_acounting.entity.customer.LocalCustomer;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "invoices")
public class LocalInvoice {

    @Id
    private String  _id;
    private String invoice_id;
    private ArrayList<Line> line;
    @DBRef
    private LocalCustomer localCustomer ;
    private String status;
    
}
