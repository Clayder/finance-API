package com.clayder.financestdd.api.fixedaccount.resource;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clayder.financestdd.api.fixedaccount.dto.FixedAccountDTO;

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
	public FixedAccountDTO create(@RequestBody FixedAccountDTO dto) {
		FixedAccount entity = modelMapper.map(dto, FixedAccount.class);
		entity = service.save(entity);
		return modelMapper.map(entity, FixedAccountDTO.class);
	}
}
