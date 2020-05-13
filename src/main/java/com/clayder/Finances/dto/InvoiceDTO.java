package com.clayder.Finances.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.domain.InvoiceInstallments;

public class InvoiceDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=2, max=80, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String name;
	
	@NotNull(message="Preenchimento obrigatório.")
	@Digits(integer=6, fraction=2)
	private Double price;
	
	@NotNull(message="Preenchimento obrigatório.")
	@Min(1)
	private Integer qtyInstallments;
	
	@NotNull(message="Preenchimento obrigatório.")
	private LocalDate dateOrder;
	
	@NotNull(message="Preenchimento obrigatório.")
	private CreditCard creditCard;
	
	private List<InvoiceInstallments> installments = new ArrayList<>();
	
	public InvoiceDTO() {
		
	}

	public InvoiceDTO(String name,Double price,Integer qtyInstallments, LocalDate dateOrder,CreditCard creditCard) {
		super();
		this.name = name;
		this.price = price;
		this.qtyInstallments = qtyInstallments;
		this.dateOrder = dateOrder;
		this.creditCard = creditCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQtyInstallments() {
		return qtyInstallments;
	}

	public void setQtyInstallments(Integer qtyInstallments) {
		this.qtyInstallments = qtyInstallments;
	}

	public LocalDate getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(LocalDate dateOrder) {
		this.dateOrder = dateOrder;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<InvoiceInstallments> getInstallments() {
		return installments;
	}

	public void setInstallments(List<InvoiceInstallments> installments) {
		this.installments = installments;
	}
	
	

}
