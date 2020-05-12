package com.clayder.Finances.services;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.Invoice;
import com.clayder.Finances.domain.InvoiceInstallments;
import com.clayder.Finances.repositories.IInvoiceInstallmentsRepository;

@Service
public class InvoiceInstallmentsService {
	
	@Autowired
	IInvoiceInstallmentsRepository repository;
	
	public void create(Invoice invoice) {		
		for(int i = 1; i <= invoice.getQtyInstallments(); i++) {
			
			InvoiceInstallments obj = new InvoiceInstallments();
			obj.setId(null);
			obj.setInvoice(invoice);
			obj.setNumber(i);
			obj.setPaid(false);
			
			LocalDate dateOrde = invoice.getDateOrder();		
			LocalDate newDate = dateOrde.plusMonths(i); 
			newDate = LocalDate.of(newDate.getYear(), newDate.getMonth(), 2);
						
			obj.setPaymentMonth(newDate);
			
			repository.save(obj);
		}
	
	}
	
}
