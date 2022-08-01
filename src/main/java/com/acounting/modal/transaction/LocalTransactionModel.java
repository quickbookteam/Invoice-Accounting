package com.acounting.modal.transaction;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LocalTransactionModel {

    private String id;
    private CustomerRef customerRef;
    private double totalAmt;
    private List<Line> line;
    private Date createTime;
    private Date lastUpdatedTime;
    private String domain;
    private String status;

}
