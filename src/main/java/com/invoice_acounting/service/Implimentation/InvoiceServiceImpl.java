package com.invoice_acounting.service.Implimentation;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.dao.InvoiceDao;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.service.InvoiceService;
import com.invoice_acounting.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        InvoiceModal invoiceModal= invoiceDao.findById(id);
                if(invoiceModal==null)
                return new ResponseEntity<> (null , HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (invoiceModal , HttpStatus.OK);
    }


    @Override
    public List<LocalInvoice> findAll()
    {
        return invoiceDao.findAll();
    }

    @Override
    public Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException {
        return invoiceDao.saveInvoiceToQuickBook(invoiceModal);
    }
    @Override
    public void saveId(String id,String localInvoiceId)
    {
        invoiceDao.saveId(id,localInvoiceId);
    }
}
