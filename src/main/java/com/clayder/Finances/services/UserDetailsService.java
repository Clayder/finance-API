package com.clayder.Finances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clayder.Finances.domain.User;
import com.clayder.Finances.repositories.IUserRepository;
import com.clayder.Finances.security.UserSecurity;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private IUserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = repo.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfile());
	}

}
