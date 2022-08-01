package com.accounting.entity.transaction;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Data
public class DepositToAccountRef {

    private String value;
    private String name;
    private String type;
}
