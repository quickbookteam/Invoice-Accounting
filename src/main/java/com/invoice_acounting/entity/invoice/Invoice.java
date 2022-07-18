package com.invoice_acounting.entity.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "invoices")
public class Invoice {

    @Id
    private String  _id;
    public ArrayList<Line> line;
    public CustomerRef customerRef;


}
