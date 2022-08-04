package com.accounting.modal.customer;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrimaryEmailAddrModal {
	@NotBlank(message = "Address cannot null")
    private String address ;

}
