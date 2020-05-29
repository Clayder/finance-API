package com.clayder.Finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayder.Finances.domain.Invoice;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long>{

}
