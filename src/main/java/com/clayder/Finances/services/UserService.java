package com.clayder.Finances.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.User;
import com.clayder.Finances.dto.UserDTO;
import com.clayder.Finances.repositories.IUserRepository;
import com.clayder.Finances.services.exceptions.DataIntegrityException;
import com.clayder.Finances.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	IUserRepository repository;
	
	public User getById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
		
	}
	
	public User insert(User obj) {
		obj.setId(null);
		if(this.existEmail(obj.getEmail())) {
			throw new DataIntegrityException("E-mail já cadastrado.");
		}
		return repository.save(obj);
	}
	
	public Boolean existEmail(String email) {
		User obj = repository.findByEmail(email);
		if (obj == null) {
			return false;
		}
		return true;
	}
	
	public User update(User obj) {
		User user = this.getById(obj.getId());
		String newEmail = obj.getEmail();
		String oldEmail = user.getEmail();
		
		System.err.println(newEmail);
		System.err.println(oldEmail);
		
		if(!newEmail.equals(oldEmail)) {
			if(this.existEmail(obj.getEmail())) {
				throw new DataIntegrityException("E-mail já cadastrado.");
			}
		}
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		this.getById(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir esse usuário.");
		}
	}
	
	public List<User> getAll() {
		return repository.findAll();
	}
	
	/**
	 * 
	 * @param page Número da página, iniciando no 0
	 * @param linesPerPage Quantidade de ítem na página
	 * @param orderBy 
	 * @param direction Ordenar de forma crescente ou decrescente
	 * @return
	 */
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	
	public User fromDTO(UserDTO objDto, User user) {
		user.setId(objDto.getId());
		user.setEmail(objDto.getEmail());
		user.setPassword(objDto.getPassword());
		return user;
	}
}
