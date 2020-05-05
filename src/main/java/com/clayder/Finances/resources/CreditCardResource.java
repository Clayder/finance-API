package com.clayder.Finances.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clayder.Finances.domain.CreditCard;

@RestController
@RequestMapping(value="/cards")
public class CreditCardResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<CreditCard> getCards() {
		
		CreditCard card = new CreditCard();
		card.setId(1);
		card.setCloseDay(12);
		card.setLimit(200.00);
		card.setName("Itau");
		card.setPaymentDay(5);
		
		CreditCard card2 = new CreditCard();
		card2.setId(2);
		card2.setCloseDay(17);
		card2.setLimit(800.00);
		card2.setName("Santander");
		card2.setPaymentDay(10);
		
		List<CreditCard> list = new ArrayList<>();
		list.add(card);
		list.add(card2);
		
		return list;
	}
}
