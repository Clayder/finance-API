package com.clayder.Finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayder.Finances.domain.CreditCard;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard, Long>{

}
