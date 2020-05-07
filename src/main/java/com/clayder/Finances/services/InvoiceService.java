package com.clayder.Finances.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.Invoice;
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
}
