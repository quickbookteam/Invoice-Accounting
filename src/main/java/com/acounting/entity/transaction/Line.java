package com.acounting.entity.transaction;

import lombok.Data;

import java.util.List;

@Data
public class Line {
    private double amount;
    private List<LinkedTxn> linkedTxn;
}
