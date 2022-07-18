package com.invoice_acounting.service.Implimentation;

import com.invoice_acounting.dao.InvoiceDao;
import com.invoice_acounting.entity.invoice.Invoice;
import com.invoice_acounting.service.InvoiceService;
import com.invoice_acounting.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    Helper helper;

    @Override
    public ResponseEntity<?> save(Invoice invoice) {
       return invoiceDao.save(invoice);
    }

    @Override
    public Invoice findById(String id) {
        Invoice invoice=invoiceDao.findById(id);
        return invoice;
    }

}
