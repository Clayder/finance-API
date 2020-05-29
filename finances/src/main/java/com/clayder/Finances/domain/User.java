package com.clayder.Finances.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.clayder.Finances.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
@ApiModel(value = "Users")
public class User  implements Serializable {

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
	
	@Column(unique=true)
	@ApiModelProperty(value="E-mail do usuário", example = "peterclayder@gmail.com", required = true)
	private String email;
	
	@JsonIgnore
	@ApiModelProperty(value="Senha", example = "XXXXXX", required = true)
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="profile")
	private Set<Integer> profile = new HashSet<>();
	
	public User() {
		addProfile(Profile.ADMIN);
	}
	

	public User(Long id, Date createdAt, Date updatedAt, String email, String password) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.email = email;
		this.password = password;
		addProfile(Profile.ADMIN);
	}
	
	public Set<Profile> getProfile(){
		return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		this.profile.add(profile.getCod());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
