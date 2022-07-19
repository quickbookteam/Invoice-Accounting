package com.invoice_acounting.modal.customer;

import com.invoice_acounting.entity.customer.BillAddr;
import com.invoice_acounting.entity.customer.PrimaryEmailAddr;
import com.invoice_acounting.entity.customer.PrimaryPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModal {

    private String _id;
    private String customer_id;
    private PrimaryEmailAddrModal primaryEmailAddr;
    private String syncToken;
    private String domain;
    private String givenName;
    private String displayName;
    private boolean billWithParent;
    private String fullyQualifiedName;
    private String companyName ;
    private String familyName ;
    private boolean sparse ;
    private PrimaryPhoneModal primaryPhone;
    private boolean active;
    private boolean job;
    private double balanceWithJobs;
    private BillAddrModal billAddr;
    private String preferredDeliveryMethod;
    private boolean taxable;
    private String printOnCheckName;
    private double balance;
    private String status;
    private Date createTime;
    private Date lastUpdatedTime;

}
