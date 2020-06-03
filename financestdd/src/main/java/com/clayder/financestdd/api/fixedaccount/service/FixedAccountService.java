package com.clayder.financestdd.api.fixedaccount.service;

import com.clayder.financestdd.api.exceptions.type.BusinessException;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.model.repository.IFixedAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class FixedAccountService implements IFixedAccountService{

    private IFixedAccountRepository repository;

    public FixedAccountService(IFixedAccountRepository repository){
        this.repository = repository;
    }

    @Override
    public FixedAccount save(FixedAccount account) {
        if( repository.existsByName(account.getName())){
            throw new BusinessException("Conta fixa já cadastrada");
        }
        return repository.save(account);
    }
}
