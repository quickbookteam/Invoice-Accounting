package com.invoice_acounting.modal;

import com.intuit.ipp.core.IEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements IEntity{

	Line line;
	CustomerRef customerRef;
	
}
