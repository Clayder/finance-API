package com.clayder.financestdd.api.fixedaccount.controller;

import com.clayder.financestdd.api.exceptions.type.ObjectNotFoundException;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clayder.financestdd.api.fixedaccount.dto.FixedAccountDTO;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fixed-accounts")
public class FixedAccountController {

    private IFixedAccountService service;
    private ModelMapper modelMapper;

    public FixedAccountController(IFixedAccountService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FixedAccountDTO create(@RequestBody @Valid FixedAccountDTO dto) {
        FixedAccount entity = modelMapper.map(dto, FixedAccount.class);
        entity = service.save(entity);
        return modelMapper.map(entity, FixedAccountDTO.class);
    }

    @GetMapping("{id}")
    public FixedAccountDTO getById(@PathVariable Long id) {

        return service
                .getById(id)
                .map(account -> modelMapper.map(account, FixedAccountDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        FixedAccount account = service.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.delete(account);
    }

    @PutMapping("{id}")
    public FixedAccountDTO update(@RequestBody @Valid FixedAccountDTO dto, @PathVariable Long id) {

        FixedAccount account = service.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        account.setName(dto.getName());
        account.setOwner(dto.getOwner());
        account.setPaymentDay(dto.getPaymentDay());
        account.setPrice(dto.getPrice());

        service.update(account);

        return modelMapper.map(account, FixedAccountDTO.class);
    }

    @GetMapping
    public Page<FixedAccountDTO> find(FixedAccountDTO dto, Pageable pageRequest){

        FixedAccount filter = modelMapper.map(dto, FixedAccount.class);
        Page<FixedAccount> resultPage = service.find(filter, pageRequest);

        List<FixedAccountDTO> list = resultPage.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, FixedAccountDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<FixedAccountDTO>(list, pageRequest, resultPage.getTotalElements());
    }
}
