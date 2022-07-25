package com.invoice_acounting.modal;

import lombok.Data;

@Data
public class ConnectionModal {
	private Long id;
	private String realmeId;
	private String accessToken;
	private String refreshToken;
	
}
