package com.clayder.Finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clayder.Finances.domain.InvoiceInstallments;

@Repository
public interface IInvoiceInstallmentsRepository extends JpaRepository<InvoiceInstallments, Long>{

}
