package com.clayder.Finances.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double limitCard;
	private Integer closeDay;
	private Integer paymentDay;
	
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
	
	
	
}
