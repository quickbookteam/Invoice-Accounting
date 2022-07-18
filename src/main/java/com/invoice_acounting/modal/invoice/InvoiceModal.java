package com.invoice_acounting.modal.invoice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceModal {
    @Id
    private String  _id;
    public ArrayList<LineModal> line;
    public CustomerRefModal customerRef;
}
