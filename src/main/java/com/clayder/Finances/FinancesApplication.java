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
		
		CreditCard card2 = new CreditCard();
		card2.setCloseDay(12);
		card2.setLimitCard(200.00);
		card2.setName("Santander");
		card2.setPaymentDay(12);
		
		CreditCard card3 = new CreditCard();
		card3.setCloseDay(12);
		card3.setLimitCard(200.00);
		card3.setName("BMG");
		card3.setPaymentDay(12);
		
		CreditCard card4 = new CreditCard();
		card4.setCloseDay(12);
		card4.setLimitCard(200.00);
		card4.setName("Bradesco");
		card4.setPaymentDay(12);
		
		
		creditCardRepository.saveAll(Arrays.asList(card, card2, card3, card4));
		
	}

}
