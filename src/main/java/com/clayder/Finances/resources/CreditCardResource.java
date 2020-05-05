package com.clayder.Finances.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/cards")
public class CreditCardResource {

	@RequestMapping(method = RequestMethod.GET)
	public String getCards() {
		return "Foi";
	}
}
