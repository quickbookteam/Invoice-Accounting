package com.accounting.modal.customer;

import java.util.Date;

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
    private String city ;
    private String line1 ;
    private String postalCode ;
    private String lat ;
    private String _long;
    private String countrySubDivisionCode;
}
