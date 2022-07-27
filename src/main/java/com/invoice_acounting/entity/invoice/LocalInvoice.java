package com.invoice_acounting.entity.invoice;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("_id")
    private String  _id;
    private String invoiceId;
    private ArrayList<Line> line;
    private CustomerRef customerRef;
    private String status;
    
}
