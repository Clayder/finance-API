package com.clayder.Finances.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Invoice update(Invoice newObj, InvoiceInstallmentsService invoiceInstallmentsService) {
		Invoice old = this.getById(newObj.getId());
		invoiceInstallmentsService.updateByQtyInstallments(newObj, old);
		return repository.save(newObj);	
	}
	
	public Invoice fromDTO(InvoiceDTO objDto, Invoice invoice) {
		
		invoice.setCreditCard(objDto.getCreditCard());
		invoice.setDateOrder(objDto.getDateOrder());
		invoice.setName(objDto.getName());
		invoice.setPrice(objDto.getPrice());
		invoice.setQtyInstallments(objDto.getQtyInstallments());

		return invoice;
	}
	
	/**
	 * 
	 * @param page Número da página, iniciando no 0
	 * @param linesPerPage Quantidade de ítem na página
	 * @param orderBy 
	 * @param direction Ordenar de forma crescente ou decrescente
	 * @return
	 */
	public Page<Invoice> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}
