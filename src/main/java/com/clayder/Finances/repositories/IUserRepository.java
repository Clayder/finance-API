package com.clayder.Finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayder.Finances.domain.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
