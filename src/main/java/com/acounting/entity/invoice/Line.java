package com.acounting.entity.invoice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Line{
    public String detailType;
    public double amount;
    public SalesItemLineDetail salesItemLineDetail;
}
