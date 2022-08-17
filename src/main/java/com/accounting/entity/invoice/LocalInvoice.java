package com.accounting.entity.invoice;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Document(collection = "invoices")
@RequiredArgsConstructor()
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LocalInvoice {

    @Id
    private String  id;
    private String invoiceId;
    private ArrayList<Line> line;
    private CustomerRef customerRef;
    private String status;
    
}

