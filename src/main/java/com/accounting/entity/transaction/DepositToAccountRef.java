package com.accounting.entity.transaction;

import lombok.Data;

@Data
public class DepositToAccountRef {

    private String value;
    private String name;
    private String type;
}
