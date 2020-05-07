package com.clayder.Finances.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clayder.Finances.domain.Invoice;
import com.clayder.Finances.services.InvoiceService;

@RestController
@RequestMapping(value="/invoices")
public class InvoiceResource {

	@Autowired
	private InvoiceService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Invoice obj = service.getById(id);
		return ResponseEntity.ok().body(obj);
	}
}
