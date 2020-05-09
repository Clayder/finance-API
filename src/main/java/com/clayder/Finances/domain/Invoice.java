package com.clayder.Finances.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;
	
	private Double price;
	private Integer qtyInstallments;
	private java.sql.Date dateOrder;
	private String name;
	
	@OneToMany(mappedBy = "invoice")
	private List<InvoiceInstallments> installments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="fk_credit_card")
	private CreditCard creditCard;
	
	public Invoice() {
		
	}
	
	public Invoice(Long id, Date createdAt, Date updatedAt, Double price, Integer qtyInstallments, java.sql.Date dateOrder,
			String name, CreditCard creditCard) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.price = price;
		this.qtyInstallments = qtyInstallments;
		this.dateOrder = dateOrder;
		this.name = name;
		this.creditCard = creditCard;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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

	public java.sql.Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(java.sql.Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	

	public List<InvoiceInstallments> getInstallments() {
		return installments;
	}

	public void Installments(List<InvoiceInstallments> installments) {
		this.installments = installments;
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
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
		
	
}