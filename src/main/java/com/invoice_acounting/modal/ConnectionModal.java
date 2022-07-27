package com.invoice_acounting.modal;

import lombok.Data;

@Data
public class ConnectionModal {
	private Long id;
	private String realmId;
	private String refreshToken;
	private String accessToken;
	private String quickBooksConnectURL;
	private String clientId;
	private String clientSecret;

	@Override
	public String toString() {
	   return "Connection{" +
	         "id=" + id +
	         ", realmId='" + realmId + '\'' +
	         ", refreshToken='" + refreshToken + '\'' +
	         ", accessToken='" + accessToken + '\'' +
	         ", quickBooksConnectURL='" + quickBooksConnectURL + '\'' +
	         ", clientId='" + clientId + '\'' +
	         ", clientSecret='" + clientSecret + '\'' +
	         '}';
	}


}
