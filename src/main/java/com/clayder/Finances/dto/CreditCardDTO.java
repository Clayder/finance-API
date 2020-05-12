package com.clayder.Finances.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.domain.Invoice;

public class CreditCardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date createdAt;
	private Date updatedAt;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=2, max=80, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String name;
	
	@NotNull(message="Preenchimento obrigatório.")
	@Digits(integer=6, fraction=2)
	private Double limitCard;
	
	@NotNull(message="Preenchimento obrigatório.")
	@Min(1)
	@Max(31)
	private Integer closeDay;
	
	@NotNull(message="Preenchimento obrigatório.")
	@Min(1)
	@Max(31)
	private Integer paymentDay;
	
	private List<Invoice> invoices = new ArrayList<>();
	
	public CreditCardDTO() {
		
	}
	
	public CreditCardDTO(CreditCard creditCard) {
		this.id = creditCard.getId();
		this.name = creditCard.getName();
		this.limitCard = creditCard.getLimitCard();
		this.closeDay = creditCard.getCloseDay();
		this.paymentDay = creditCard.getPaymentDay();
		this.invoices = creditCard.getInvoices();
	}
	
	public CreditCardDTO(Long id, String name, Double limitCard, Integer closeDay, Integer paymentDay) {
		this.id = id;
		this.name = name;
		this.limitCard = limitCard;
		this.closeDay = closeDay;
		this.paymentDay = paymentDay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLimitCard() {
		return limitCard;
	}

	public void setLimitCard(Double limitCard) {
		this.limitCard = limitCard;
	}

	public Integer getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(Integer closeDay) {
		this.closeDay = closeDay;
	}

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
	

}
