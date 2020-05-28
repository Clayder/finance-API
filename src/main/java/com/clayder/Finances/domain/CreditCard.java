package com.clayder.Finances.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "credit_card")
@ApiModel(value = "CreditCard")
public class CreditCard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="ID do cartão de crédito", example = "12", required = true)
	private Long id;
	
	@CreationTimestamp
	@Column(name = "createdAt", nullable = false, updatable=false)
	@ApiModelProperty(value="Data de criação do cartão de crédito", example = "2020-05-19T16:16:52.524Z", required = true)
	private Date createdAt;
	
	@UpdateTimestamp
	@ApiModelProperty(value="Data de atualização do cartão de crédito", example = "2020-05-19T16:16:52.524Z", required = true)
	private Date updatedAt;
	
	@ApiModelProperty(value="Nome do cartão de crédito", example = "Itaú", required = true)
	private String name;
	
	@ApiModelProperty(value="Valor limite do cartão de crédito", example = "800.00", required = true)
	private Double limitCard;
	
	@ApiModelProperty(value="Data de fechamento da fatura do cartão de crédito", example = "20", required = true)
	private Integer closeDay;
	
	@ApiModelProperty(value="Dia de pagamento do cartão de crédito", example = "10", required = true)
	private Integer paymentDay;
	
	private Integer teste;
	
	@JsonBackReference
	@OneToMany(mappedBy = "creditCard")
	@ApiModelProperty(value="Faturas do cartão de crédito", example = "[1,3,6,8]")
	private List<Invoice> invoices = new ArrayList<>();
	
	public CreditCard() {
	}

	public CreditCard(Long id, String name, Double limitCard, Integer closeDay, Integer paymentDay) {
		super();
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
	
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getTeste() {
		return teste;
	}

	public void setTeste(Integer teste) {
		this.teste = teste;
	}
	
	
	
}
