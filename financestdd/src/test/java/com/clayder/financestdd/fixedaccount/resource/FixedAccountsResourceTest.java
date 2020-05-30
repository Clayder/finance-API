package com.clayder.financestdd.fixedaccount.resource;

import com.clayder.financestdd.api.fixedaccount.dto.FixedAccountDTO;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	@DisplayName("Deve criar uma conta com sucesso.")
	public void createFixedAccountTest() throws Exception {

		FixedAccountDTO dto = FixedAccountDTO.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();

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
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("name").value(dto.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("price").value(dto.getPrice()))
				.andExpect(MockMvcResultMatchers.jsonPath("paymentDay").value(dto.getPaymentDay()))
				.andExpect(MockMvcResultMatchers.jsonPath("owner").value(dto.getOwner()));

	}

	@Test
	@DisplayName("Erro ao criar uma conta.")
	public void createFixedAccountErrorTest() {

	}
}
