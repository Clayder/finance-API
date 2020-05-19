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

import com.clayder.Finances.domain.CreditCard;
import com.clayder.Finances.dto.CreditCardDTO;
import com.clayder.Finances.messages.ResponseError;
import com.clayder.Finances.services.CreditCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(value="/api/v1/cards")
@Api(value = "/cards", tags = "Cartão de crédito", description = "API para realizar o gerenciamento de cartão de crédito.")
public class CreditCardResource {

	@Autowired
	private CreditCardService cardService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces="application/json")
	@ApiOperation(value = "Retorna um cartões de crédito específico.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK."),
		    @ApiResponse(code = 401, message = "Não autorizado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 404, message = "Não encontrado."),
		    @ApiResponse(code = 405, message = "Método não permitido"),
		    @ApiResponse(code = 415, message = "Tipo de mídia não suportado."),
		    @ApiResponse(code = 500, message = "Erro interno."),
		})
	public ResponseEntity<CreditCardDTO> getById(@PathVariable Long id) {
		CreditCard card = cardService.getById(id);
		CreditCardDTO obj = new CreditCardDTO(card);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastro de cartão de crédito.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "OK", responseHeaders = {@ResponseHeader(name = "location", response = URI.class) }),
		    @ApiResponse(code = 400, message = "Requisição inválida.", response = ResponseError.class),
		    @ApiResponse(code = 401, message = "Não autorizado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 405, message = "Método não permitido"),
		    @ApiResponse(code = 415, message = "Tipo de mídia não suportado."),
		    @ApiResponse(code = 500, message = "Erro interno."),
		})
	public ResponseEntity<Void> insert(@Valid @RequestBody CreditCardDTO objDTO ){
		CreditCard obj = cardService.fromDTO(objDTO, new CreditCard());
		obj = cardService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Atualizar dados do cartão de crédito.")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "OK"),
		    @ApiResponse(code = 400, message = "Requisição inválida.", response = ResponseError.class),
		    @ApiResponse(code = 401, message = "Não autorizado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 404, message = "Não encontrado."),
		    @ApiResponse(code = 405, message = "Método não permitido"),
		    @ApiResponse(code = 415, message = "Tipo de mídia não suportado."),
		    @ApiResponse(code = 500, message = "Erro interno."),
		})
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
	public ResponseEntity<Void> update(@Valid @RequestBody CreditCardDTO objDTO, @PathVariable Long id){
		CreditCard obj = cardService.fromDTO(objDTO, new CreditCard());
		obj.setId(id);
		obj = cardService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deletar cartão de crédito.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 401, message = "Não autorizado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 404, message = "Não encontrado."),
		    @ApiResponse(code = 405, message = "Método não permitido"),
		    @ApiResponse(code = 415, message = "Tipo de mídia não suportado."),
		    @ApiResponse(code = 422, message = "Entidade improcessável.", response = ResponseError.class),
		    @ApiResponse(code = 500, message = "Erro interno."),
		})
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cardService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Retorna os cartões de crédito.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 401, message = "Não autorizado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 405, message = "Método não permitido"),
		    @ApiResponse(code = 415, message = "Tipo de mídia não suportado."),
		    @ApiResponse(code = 500, message = "Erro interno."),
		})
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Page<CreditCard>> getAll(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "name")String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		Page<CreditCard> list = cardService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	
}
