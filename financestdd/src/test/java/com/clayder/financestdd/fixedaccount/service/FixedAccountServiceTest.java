package com.clayder.financestdd.fixedaccount.service;

import com.clayder.financestdd.api.exceptions.type.BusinessException;
import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.model.repository.IFixedAccountRepository;
import com.clayder.financestdd.api.fixedaccount.service.FixedAccountService;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class FixedAccountServiceTest {

    IFixedAccountService service;

    @MockBean
    IFixedAccountRepository repository;

    /**
     * Método será executado antes de cada teste
     */
    @BeforeEach
    public void setUp(){
        this.service = new FixedAccountService(repository);
    }

    @Test
    @DisplayName("Salvando conta fixa com sucesso.")
    public void saveFixedAccountTest(){

        // cenario
        FixedAccount account = createValidAccount();

        /**
         * Quando for executado o método existsByName do repository, retornar false
         * dessa forma vamos conseguir simular que não existe conta fixa cadastrada.
         */
        Mockito.when( repository.existsByName(Mockito.anyString()) ).thenReturn(false);

        /**
         * Simulando o comportamento do repository
         * Quando salvar a conta returnar a conta de exemplo
         */
        Mockito.when( repository.save(account) ).thenReturn(
                FixedAccount.builder()
                        .id((long) 1)
                        .name("Vivo")
                        .owner("Peter")
                        .paymentDay(22)
                        .price(54.99)
                        .build()
        );

        // executando o teste
        FixedAccount save = service.save(account);

        // verificação
        assertThat(save.getId()).isNotNull();
        assertThat(save.getName()).isEqualTo("Vivo");
        assertThat(save.getOwner()).isEqualTo("Peter");
        assertThat(save.getPaymentDay()).isEqualTo(22);
        assertThat(save.getPrice()).isEqualTo(54.99);
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar salvar uma conta fixa com nome duplicado.")
    public void shouldNotSaveAccountWithDuplicatedName(){
        // cenario
        FixedAccount account = createValidAccount();

        /**
         * Quando for executado o método existsByName do repository, retornar true
         * dessa forma vamos conseguir simular que já essa conta fixa cadastrada.
         */
        Mockito.when( repository.existsByName(Mockito.anyString()) ).thenReturn(true);

        //execução
        Throwable exception = Assertions.catchThrowable(() -> service.save(account));

        // verificações
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Conta fixa já cadastrada");

        /**
         * Verifica que nunca será executado o método save
         */
        Mockito.verify(repository, Mockito.never()).save(account);
    }

    private FixedAccount createValidAccount(){
        return FixedAccount.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();
    }
}
