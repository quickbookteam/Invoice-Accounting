package com.invoice_acounting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "connection")
public class Connection {

	@Id
	private Long id;
	private String realmId;
	private String refershToken;
	private String accessToken;

}
