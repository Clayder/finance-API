package com.clayder.Finances.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.Invoice;
import com.clayder.Finances.dto.InvoiceDTO;
import com.clayder.Finances.repositories.IInvoiceRepository;
import com.clayder.Finances.services.exceptions.ObjectNotFoundException;

@Service
public class InvoiceService {
	
	@Autowired
	IInvoiceRepository repository;
	
	public Invoice getById(Long id) {
		Optional<Invoice> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Fatura não encontrada."));
	}
	
	public Invoice insert(Invoice obj, InvoiceInstallmentsService invoiceInstallmentsService) {
		obj.setId(null);
		Invoice newObj = repository.save(obj);
		invoiceInstallmentsService.create(newObj);
		return newObj;
	}
	
	public Invoice fromDTO(InvoiceDTO objDto, Invoice invoice) {
		
		invoice.setCreditCard(objDto.getCreditCard());
		invoice.setDateOrder(objDto.getDateOrder());
		invoice.setName(objDto.getName());
		invoice.setPrice(objDto.getPrice());
		invoice.setQtyInstallments(objDto.getQtyInstallments());

		return invoice;
	}
}
