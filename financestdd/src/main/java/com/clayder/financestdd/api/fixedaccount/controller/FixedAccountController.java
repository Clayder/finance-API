package com.clayder.financestdd.api.fixedaccount.controller;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clayder.financestdd.api.fixedaccount.dto.FixedAccountDTO;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fixed-accounts")
public class FixedAccountController {

	private IFixedAccountService service;
	private ModelMapper modelMapper;

	public FixedAccountController(IFixedAccountService service, ModelMapper modelMapper){
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
	public FixedAccountDTO getById(@PathVariable Long id){

		return service
				.getById(id)
				.map( account -> modelMapper.map(account, FixedAccountDTO.class) )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}
}
