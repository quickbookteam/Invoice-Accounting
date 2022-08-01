package com.accounting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "connection")
public class Connection {

	@Id
	private Long id;
	private String realmId;
	private String refreshToken;
	private String accessToken;
	private String quickBooksConnectURL;
	private String clientId;
	private String clientSecret;

}
	
