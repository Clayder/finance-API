package com.clayder.Finances.resources;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clayder.Finances.domain.User;
import com.clayder.Finances.dto.UserDTO;
import com.clayder.Finances.messages.ResponseError;
import com.clayder.Finances.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(value="/api/v1/users")
@Api(value = "/users", tags = "Usuários", description = "API para realizar o gerenciamento de usuários.")
public class UserResource extends Resource{

	@Autowired
	private UserService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces="application/json")
	@ApiOperation(value = "Retorna um usuário específico.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = status200Message),
		    @ApiResponse(code = 401, message = status401Message),
		    @ApiResponse(code = 403, message = status403Message),
		    @ApiResponse(code = 404, message = status404Message),
		    @ApiResponse(code = 405, message = status405Message),
		    @ApiResponse(code = 415, message = status415Message),
		    @ApiResponse(code = 500, message = status500Message),
		})
	public ResponseEntity<User> getById(@PathVariable Long id) {
		User user = service.getById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastro de usuário.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = status201Message, responseHeaders = {@ResponseHeader(name = "location", response = URI.class) }),
		    @ApiResponse(code = 400, message = status400Message, response = ResponseError.class),
		    @ApiResponse(code = 401, message = status401Message),
		    @ApiResponse(code = 403, message = status403Message),
		    @ApiResponse(code = 405, message = status405Message),
		    @ApiResponse(code = 415, message = status415Message),
		    @ApiResponse(code = 500, message = status500Message),
		})
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDTO ){
		User obj = service.fromDTO(objDTO, new User());
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Atualizar dados de um usuário.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = status204Message),
		    @ApiResponse(code = 400, message = status400Message, response = ResponseError.class),
		    @ApiResponse(code = 401, message = status401Message),
		    @ApiResponse(code = 403, message = status403Message),
		    @ApiResponse(code = 404, message = status404Message),
		    @ApiResponse(code = 405, message = status405Message),
		    @ApiResponse(code = 415, message = status415Message),
		    @ApiResponse(code = 500, message = status500Message),
		})
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDTO, @PathVariable Long id){
		User obj = service.fromDTO(objDTO, new User());
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Excluir usuário.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = status200Message),
		    @ApiResponse(code = 401, message = status401Message),
		    @ApiResponse(code = 403, message = status403Message),
		    @ApiResponse(code = 404, message = status404Message),
		    @ApiResponse(code = 405, message = status405Message),
		    @ApiResponse(code = 415, message = status415Message),
		    @ApiResponse(code = 422, message = status422Message, response = ResponseError.class),
		    @ApiResponse(code = 500, message = status500Message),
		})
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Retornar todos os usuários.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = status200Message),
		    @ApiResponse(code = 401, message = status401Message),
		    @ApiResponse(code = 403, message = status403Message),
		    @ApiResponse(code = 405, message = status405Message),
		    @ApiResponse(code = 415, message = status415Message),
		    @ApiResponse(code = 500, message = status500Message),
		})
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Page<User>> getAll(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "email")String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	
}
