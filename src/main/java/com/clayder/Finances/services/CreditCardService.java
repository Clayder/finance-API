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
}
