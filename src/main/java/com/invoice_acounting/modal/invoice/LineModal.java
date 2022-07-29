package com.invoice_acounting.modal.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineModal {
    public String detailType;
    public double amount;
    public SalesItemLineDetailModal salesItemLineDetail;
}
