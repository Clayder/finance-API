package com.clayder.financestdd.fixedaccount.controller;

import com.clayder.financestdd.api.fixedaccount.dto.FixedAccountDTO;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.exceptions.type.BusinessException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class FixedAccountsResourceTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	IFixedAccountService service;

	private final String FIXED_ACCOUNT_API = "/api/v1/fixed-accounts";

	@Test
	@DisplayName("[201] Criando conta fixa com sucesso.")
	public void createFixedAccountTest() throws Exception {

		FixedAccountDTO dto = createNewAccount();

		FixedAccount saveFixedAccount = FixedAccount.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.id(1)
				.build();

		BDDMockito.given(service.save(Mockito.any(FixedAccount.class)))
				.willReturn(saveFixedAccount);

		String json = new ObjectMapper().writeValueAsString(dto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(FIXED_ACCOUNT_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").isNotEmpty())
				.andExpect(jsonPath("name").value(dto.getName()))
				.andExpect(jsonPath("price").value(dto.getPrice()))
				.andExpect(jsonPath("paymentDay").value(dto.getPaymentDay()))
				.andExpect(jsonPath("owner").value(dto.getOwner()));

	}

	@Test
	@DisplayName("[400] Criando conta com body vazio.")
	public void createFixedAccountErrorTest() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new FixedAccountDTO());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(FIXED_ACCOUNT_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request)
				.andExpect( status().isBadRequest())
				.andExpect( jsonPath("errors", Matchers.hasSize(3)))
				.andExpect( jsonPath("status", Matchers.equalTo(400)))
				.andExpect( jsonPath("msg", Matchers.equalTo("Erro de validação")))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
	}

	@Test
	@DisplayName("[422] Deve lançar erro ao tentar cadastrar uma conta fixa com nome repetido.")
	public void creteFixedAccountWithDuplicatedName() throws Exception {

		FixedAccountDTO dto = createNewAccount();
		String json = new ObjectMapper().writeValueAsString(dto);
		String message = "Conta fixa já cadastrada";

		BDDMockito.given(service.save(Mockito.any(FixedAccount.class)))
				.willThrow(new BusinessException(message));

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(FIXED_ACCOUNT_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request)
				.andExpect( status().isUnprocessableEntity())
				.andExpect( jsonPath("status", Matchers.equalTo(422)))
				.andExpect( jsonPath("msg").value(message))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
	}

	private FixedAccountDTO createNewAccount(){
		return FixedAccountDTO.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();
	}
}