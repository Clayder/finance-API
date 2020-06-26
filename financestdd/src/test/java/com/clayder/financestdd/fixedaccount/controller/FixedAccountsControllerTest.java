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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.Media;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class FixedAccountsControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	IFixedAccountService service;

	private final String FIXED_ACCOUNT_API = "/api/v1/fixed-accounts";

	@Test
	@DisplayName("[201] Criando conta fixa com sucesso.")
	public void createFixedAccountTest() throws Exception {

		FixedAccountDTO dto = createNewAccountDTO();

		FixedAccount saveFixedAccount = FixedAccount.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.id((long) 1)
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
				.andExpect( jsonPath("errors", Matchers.hasSize(4)))
				.andExpect( jsonPath("status", Matchers.equalTo(400)))
				.andExpect( jsonPath("msg", Matchers.equalTo("Erro de validação")))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
	}

	@Test
	@DisplayName("[400] Criando conta fixa com dados inválidos.")
	public void createFixedAccountInvalidDataTest() throws Exception {

		FixedAccountDTO account = FixedAccountDTO.builder()
				.name("V")
				.owner("P")
				.paymentDay(1000)
				.price(0)
				.build();

		String json = new ObjectMapper().writeValueAsString(account);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(FIXED_ACCOUNT_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);

		mvc.perform(request)
				.andExpect( status().isBadRequest())
				.andExpect( jsonPath("errors", Matchers.hasSize(4)))
				.andExpect( jsonPath("status", Matchers.equalTo(400)))
				.andExpect( jsonPath("msg", Matchers.equalTo("Erro de validação")))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
	}

	@Test
	@DisplayName("[422] Deve lançar erro ao tentar cadastrar uma conta fixa com nome repetido.")
	public void creteFixedAccountWithDuplicatedName() throws Exception {

		FixedAccountDTO dto = createNewAccountDTO();
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

	@Test
	@DisplayName("[200] Deve obter informações de uma conta fixa.")
	public void getFixedAccountDetailsIsTest() throws Exception {

		// cenario
		Long id = 1L;

		FixedAccount account = FixedAccount.builder()
				.id(id)
				.name(createNewAccountDTO().getName())
				.owner(createNewAccountDTO().getOwner())
				.paymentDay(createNewAccountDTO().getPaymentDay())
				.price(createNewAccountDTO().getPrice())
				.build();

		BDDMockito.given(service.getById(id)).willReturn(Optional.of(account));

		//execução

		 MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(FIXED_ACCOUNT_API.concat("/"+id))
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(1))
				.andExpect(jsonPath("name").value(createNewAccountDTO().getName()))
				.andExpect(jsonPath("price").value(createNewAccountDTO().getPrice()))
				.andExpect(jsonPath("paymentDay").value(createNewAccountDTO().getPaymentDay()))
				.andExpect(jsonPath("owner").value(createNewAccountDTO().getOwner()));

	}

	@Test
	@DisplayName("[404] Deve retornar resource not found quando a conta não existir.")
	public void fixedAccountNotFoundTest()  throws Exception{

		BDDMockito
				.given(service.getById( anyLong() )) // sempre que pesquisar por qualquer long id
				.willReturn(Optional.empty()); // retornar vazio

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(FIXED_ACCOUNT_API.concat("/1"))
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("[204] Deve deletar uma conta fixa.")
	public void deleteFixedAccountTest() throws Exception{
		// cenario
		BDDMockito.
				given( service.getById( anyLong() ) ) // Sempre que pesquisar por qualquer long id
				.willReturn(
						Optional.of( FixedAccount.builder().id(1L).build() )
				); // retornar uma conta com ID = 1

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete(FIXED_ACCOUNT_API.concat("/"+1));

		mvc.perform( request )
				.andExpect( status().isNoContent() );
	}

	@Test
	@DisplayName("[404] Deve retornar resource not found quando não encontrar a conta para deletar.")
	public void deleteInexistentFixedAccountTest() throws Exception{
		// cenario
		BDDMockito.
				given( service.getById( anyLong() ) ) // Sempre que pesquisar por qualquer long id
				.willReturn(
						Optional.empty()
				); // retornar vazio

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete(FIXED_ACCOUNT_API.concat("/"+1));

		mvc.perform( request )
				.andExpect( status().isNotFound() );
	}

	@Test
    @DisplayName("[200] Deve atualizar os dados da conta")
	public void updateFixedAccountTest() throws Exception{
        // cenario

        Long id = 1L;

        // cenario
        FixedAccount accountUpdate = FixedAccount.builder()
                .id(id)
                .name("Tim")
				.owner("Fernanda")
				.paymentDay(28)
				.price(70.00)
                .build();

        String json = new ObjectMapper().writeValueAsString(accountUpdate);

        BDDMockito.
				given( service.getById(id) ) // Sempre que pesquisar pelo id
				.willReturn(Optional.of(
				        FixedAccount.builder()
                                .id(id)
                                .name("Vivo")
                                .owner("Peter")
                                .paymentDay(22)
                                .price(54.99)
                                .build()
                )); // retornar os dados antigos

		/**
		 * Sempre que atualizar uma conta, retornar a propria conta
		 */
        BDDMockito.given(service.update(accountUpdate)).willReturn(accountUpdate);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(FIXED_ACCOUNT_API.concat("/"+id))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

		mvc.perform( request )
				.andExpect( status().isOk() )
				.andExpect(jsonPath("id").value(accountUpdate.getId()))
				.andExpect(jsonPath("name").value(accountUpdate.getName()))
				.andExpect(jsonPath("price").value(accountUpdate.getPrice()))
				.andExpect(jsonPath("paymentDay").value(accountUpdate.getPaymentDay()))
				.andExpect(jsonPath("owner").value(accountUpdate.getOwner()));
    }

    @Test
    @DisplayName("[400] Atualizando conta fixa com dados vazios")
	public void updateFixedAccountEmptyTest() throws Exception{
        // cenario

        Long id = 1L;

        String json = new ObjectMapper().writeValueAsString(new FixedAccountDTO());

        BDDMockito.
				given( service.getById(id) ) // Sempre que pesquisar pelo id
				.willReturn(Optional.of(
				        FixedAccount.builder()
                                .id(id)
                                .name("Vivo")
                                .owner("Peter")
                                .paymentDay(22)
                                .price(54.99)
                                .build()
                )); // retornar os dados antigos

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(FIXED_ACCOUNT_API.concat("/"+id))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect( status().isBadRequest())
				.andExpect( jsonPath("errors", Matchers.hasSize(4)))
				.andExpect( jsonPath("status", Matchers.equalTo(400)))
				.andExpect( jsonPath("msg", Matchers.equalTo("Erro de validação")))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
    }

    @Test
    @DisplayName("[400] Atualizando conta fixa com dados inválidos")
	public void updateFixedAccountInvalidDataTest() throws Exception{
        // cenario

        Long id = 1L;

        // cenario
        FixedAccountDTO account = FixedAccountDTO.builder()
				.name("V")
				.owner("P")
				.paymentDay(1000)
				.price(0)
				.build();

        String json = new ObjectMapper().writeValueAsString(account);

        BDDMockito.
				given( service.getById(id) ) // Sempre que pesquisar pelo id
				.willReturn(Optional.of(
				        FixedAccount.builder()
                                .id(id)
                                .name("Vivo")
                                .owner("Peter")
                                .paymentDay(22)
                                .price(54.99)
                                .build()
                )); // retornar os dados antigos

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(FIXED_ACCOUNT_API.concat("/"+id))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

		mvc.perform(request)
				.andExpect( status().isBadRequest())
				.andExpect( jsonPath("errors", Matchers.hasSize(4)))
				.andExpect( jsonPath("status", Matchers.equalTo(400)))
				.andExpect( jsonPath("msg", Matchers.equalTo("Erro de validação")))
				.andExpect( jsonPath("timeStamp").isNotEmpty());
    }

    @Test
    @DisplayName("[404] Deve retornar 404 ao tentar atualizar uma conta inexistente.")
	public void updateInexistentFixedAccountTest() throws Exception {
         // cenario
        String json = new ObjectMapper().writeValueAsString(createNewAccountDTO());

		// cenario
        FixedAccount accountUpdate = FixedAccount.builder()
                .id(1L)
                .name("Tim")
				.owner("Fernanda")
				.paymentDay(28)
				.price(70.00)
                .build();
        BDDMockito.
				given( service.getById( anyLong() ) ) // Sempre que pesquisar por qualquer long id
				.willReturn(
						Optional.empty() // retornar vazio
				);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(FIXED_ACCOUNT_API.concat("/"+1))
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

		mvc.perform( request )
				.andExpect( status().isNotFound() );
    }

    @Test
	@DisplayName("[200] Deve filtrar as contas fixas.")
    public void findFixedAccount() throws Exception {

		/**
		 * Criando uma nova conta
		 */
		Long id = 1L;
		FixedAccount account = createNewAccount();
		account.setId(id);

		/**
		 * Simulando a paginação
		 */
		PageImpl<FixedAccount> accountPage = new PageImpl<FixedAccount>(Arrays.asList(account), PageRequest.of(0, 100), 1);
		BDDMockito.given( service.find( Mockito.any( FixedAccount.class ), Mockito.any(Pageable.class) ) )
				.willReturn(accountPage);


		/**
		 * Realizando a consulta
		 */
		String queryString = String.format("?name=%s&page=0&size=100", account.getName() );
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(FIXED_ACCOUNT_API.concat(queryString))
				.accept(MediaType.APPLICATION_JSON);

		/**
		 * Verificando o resultado
		 */
		mvc
				.perform( request )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "content", Matchers.hasSize(1) ) )
				.andExpect( jsonPath( "totalElements").value(1))
				.andExpect( jsonPath( "pageable.pageSize").value(100) )
				.andExpect( jsonPath( "pageable.pageNumber").value(0) )
		;

	}

    private FixedAccountDTO createNewAccountDTO(){
		return FixedAccountDTO.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();
	}

	private FixedAccount createNewAccount(){
		return FixedAccount.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();
	}
}
