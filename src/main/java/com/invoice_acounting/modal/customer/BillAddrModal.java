package com.invoice_acounting.modal.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillAddrModal {
    private Long id;
    private String city ;
    private String line1 ;
    private String postalCode ;
    private String lat ;
    private String _long;
    private String countrySubDivisionCode;
}
