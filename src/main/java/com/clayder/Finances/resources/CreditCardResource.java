package com.clayder.Finances.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.services.CreditCardService;

@RestController
@RequestMapping(value="/cards")
public class CreditCardResource {

	@Autowired
	private CreditCardService cardService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		CreditCard obj = cardService.getById(id);
		return ResponseEntity.ok().body(obj);
	}
}
