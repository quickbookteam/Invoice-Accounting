package com.invoice_acounting.scheduler;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.dao.InvoiceDao;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.service.InvoiceService;
import com.invoice_acounting.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Date;
import java.util.List;

@Configuration
public class InvoiceScheduler {

    @Autowired
    Helper helper;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceDao invoiceDao;

    @Scheduled(cron = "0 * * ? * *")//after every minute
    public Invoice  saveInvoiceToQuickBookServer() throws FMSException {
        System.out.println(new Date());
        List<LocalInvoice> localInvoices=invoiceDao.getInvoices_With_Created_Status();
        for(LocalInvoice localInvoice:localInvoices)
        {
            InvoiceModal invoiceModal=invoiceDao.findById(localInvoice.get_id());

           Invoice invoice= invoiceService.saveInvoiceToQuickBook(invoiceModal);
           invoiceService.saveId(invoice.getId(),localInvoice.get_id());
        }

        return null;
    }
}
