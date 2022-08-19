package com.accounting.entity.transaction;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document("transactions")
@Data
public class LocalTransaction {
    @Id
    private String id;
    private CustomerRef customerRef;
    private double totalAmt;
    private List<Line> line;
    private Date createTime;
    private Date lastUpdatedTime;
    private String domain;
    private String status;
    private DepositToAccountRef depositToAccountRef;
    private Double unappliedAmt;

}
