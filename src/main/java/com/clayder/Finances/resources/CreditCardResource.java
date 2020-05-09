package com.clayder.Finances.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.services.CreditCardService;

@RestController
@RequestMapping(value="/cards")
public class CreditCardResource {

	@Autowired
	private CreditCardService cardService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<CreditCard> getById(@PathVariable Long id) {
		CreditCard obj = cardService.getById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody CreditCard obj){
		obj = cardService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CreditCard obj, @PathVariable Long id){
		obj.setId(id);
		obj = cardService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cardService.delete(id);
		return ResponseEntity.ok().build();
	}
}
