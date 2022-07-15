package com.invoice_acounting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="connection")
public class Connection {
	
@Id
  private Long id;
  private String realmId;
  private String refershToken;
  @Column(length = 1337)
  private String accessToken;
}
