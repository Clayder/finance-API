package com.clayder.Finances.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "invoices")
@ApiModel(value = "Invoice")
public class Invoice implements Serializable {

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
	
	@ApiModelProperty(value="Valor da compra", example = "200", required = true)
	private Double price;
	
	@ApiModelProperty(value="Quantidade de parcelas", example = "1", required = true)
	private Integer qtyInstallments;
	
	@ApiModelProperty(value="Data da compra", example = "2020-05-16", required = true)
	private LocalDate dateOrder;
	
	@ApiModelProperty(value="Nome da compra", example = "Pipoqueira Elétrica", required = true)
	private String name;
	
	@ApiModelProperty(value="Listagem de parcelas", required = true)
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "invoice")
	private List<InvoiceInstallments> installments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="fk_credit_card")
	@ApiModelProperty(value="Dados do cartão", required = true)
	private CreditCard creditCard;
	
	public Invoice() {
		
	}
	
	public Invoice(Long id, Date createdAt, Date updatedAt, Double price, Integer qtyInstallments, LocalDate dateOrder,
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

	public LocalDate getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(LocalDate dateOrder) {
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
