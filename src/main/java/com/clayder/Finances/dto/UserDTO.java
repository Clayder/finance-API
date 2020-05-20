package com.clayder.Finances.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.clayder.Finances.domain.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserUI")
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(readOnly = true)
	private Long id;
	
	@ApiModelProperty(readOnly = true)
	private Date createdAt;
	
	@ApiModelProperty(readOnly = true)
	private Date updatedAt;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	@ApiModelProperty(value="E-mail do usuário", example = "peterclayder@gmail.com", required = true)
	private String email;
	
	@ApiModelProperty(value="Senha", example = "XXXXXX", required = true)
	@NotEmpty(message="Preenchimento obrigatório.")
	private String password;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, Date createdAt, Date updatedAt, String email, String password) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
		this.email = user.getEmail();
		this.password = user.getPassword();
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
	
	
	
	
	

}
