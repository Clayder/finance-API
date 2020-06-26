package com.clayder.financestdd.api.fixedaccount.service;

import com.clayder.financestdd.api.exceptions.type.BusinessException;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.model.repository.IFixedAccountRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FixedAccountService implements IFixedAccountService {

    private IFixedAccountRepository repository;

    public FixedAccountService(IFixedAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public FixedAccount save(FixedAccount account) {
        if (repository.existsByName(account.getName())) {
            throw new BusinessException("Conta fixa já cadastrada");
        }
        account.setId(null);
        return repository.save(account);
    }

    @Override
    public Optional<FixedAccount> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(FixedAccount account) {
        if (account == null || account.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }
        this.repository.delete(account);
    }

    @Override
    public FixedAccount update(FixedAccount account) {
        if (account == null || account.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        }
        return this.repository.save(account);
    }

    @Override
    public Page<FixedAccount> find(FixedAccount filter, Pageable pageRequest) {

        Example<FixedAccount> example = Example.of(filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(
                                ExampleMatcher.StringMatcher.CONTAINING
                        ));

        return repository.findAll(example, pageRequest);
    }
}
