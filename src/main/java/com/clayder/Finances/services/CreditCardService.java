package com.clayder.Finances.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.repositories.ICreditCardRepository;
import com.clayder.Finances.services.exceptions.ObjectNotFoundException;

@Service
public class CreditCardService {
	
	@Autowired
	ICreditCardRepository repository;
	
	public CreditCard getById(Long id) {
		Optional<CreditCard> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cartão não encontrado"));
		
	}
	
	public CreditCard insert(CreditCard obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public CreditCard update(CreditCard obj) {
		this.getById(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		this.getById(id);
		repository.deleteById(id);
	}
}
