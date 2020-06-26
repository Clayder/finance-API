package com.clayder.financestdd.api.fixedaccount.service;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface IFixedAccountService {

    FixedAccount save(FixedAccount account);

    Optional<FixedAccount> getById(Long id);

    void delete(FixedAccount account);

    FixedAccount update(FixedAccount account);

    Page<FixedAccount> find(FixedAccount filter, Pageable pageRequest);
}
