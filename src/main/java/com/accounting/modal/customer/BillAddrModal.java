package com.accounting.modal.customer;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillAddrModal {
    private Long id;
    @NotBlank
    private String city ;
    @NotBlank
    private String line1 ;
    @NotBlank
    private String postalCode ;
    private String lat ;
    private String _long;
    private String countrySubDivisionCode;
}
