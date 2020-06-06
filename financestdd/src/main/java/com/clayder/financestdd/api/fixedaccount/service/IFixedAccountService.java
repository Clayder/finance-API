package com.clayder.financestdd.api.fixedaccount.service;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;

import java.util.Optional;

public interface IFixedAccountService {

    FixedAccount save(FixedAccount account);

    Optional<FixedAccount> getById(Long id);

    void delete(FixedAccount account);
}
