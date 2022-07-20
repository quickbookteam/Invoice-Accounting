package com.invoice_acounting.service.Implimentation;

import com.invoice_acounting.dao.InvoiceDao;
import com.invoice_acounting.modal.invoice.InvoiceModal;
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
    public ResponseEntity<?> save(InvoiceModal invoice) {
       return invoiceDao.save(invoice);
    }

    @Override
    public ResponseEntity<?> findById(String id) {
        return invoiceDao.findById(id);
    }

}
