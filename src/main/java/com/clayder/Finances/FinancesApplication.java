package com.clayder.Finances;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.repositories.ICreditCardRepository;

@SpringBootApplication
public class FinancesApplication implements CommandLineRunner{
	
	@Autowired
	private ICreditCardRepository creditCardRepository; 

	public static void main(String[] args) {
		SpringApplication.run(FinancesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		CreditCard card = new CreditCard();
		card.setCloseDay(12);
		card.setLimitCard(200.00);
		card.setName("Itaú");
		card.setPaymentDay(12);
		
		creditCardRepository.saveAll(Arrays.asList(card));
		
	}

}
