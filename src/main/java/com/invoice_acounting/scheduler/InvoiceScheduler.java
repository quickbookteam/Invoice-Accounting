//package com.invoice_acounting.scheduler;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.intuit.ipp.data.Invoice;
//import com.intuit.ipp.exception.FMSException;
//import com.invoice_acounting.entity.invoice.LocalInvoice;
//import com.invoice_acounting.modal.invoice.InvoiceModal;
//import com.invoice_acounting.service.SchedularService;
//import com.invoice_acounting.service.Implimentation.SchedularServiceImpl;
//import com.invoice_acounting.util.Helper;
//
//@Configuration
//public class InvoiceScheduler {
//
//    @Autowired
//    Helper helper;
//
//    @Autowired
//    SchedularServiceImpl schedularService;
//    
//    
//    
//
//
////    @Scheduled(cron = "0 * * ? * *")//after every minute
////    public Invoice  saveInvoiceToQuickBookServer() throws FMSException {
////        System.out.println(new Date());
////        List<LocalInvoice> localInvoices=schedularService.getInvoices_With_Created_Status();
////        for(LocalInvoice localInvoice:localInvoices)
////        {
////        	InvoiceModal invoiceModal=schedularService.findInvoiceById(localInvoice.get_id());
////
////           Invoice invoice= schedularService.saveInvoiceToQuickBook(invoiceModal);
////           schedularService.saveId(invoice.getId(),localInvoice.get_id());
////        }
////
////        return null;
////    }
//}
