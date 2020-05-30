package com.clayder.financestdd.api.fixedaccount.model.repository;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFixedAccountRepository extends JpaRepository<FixedAccount, Long> {

}
