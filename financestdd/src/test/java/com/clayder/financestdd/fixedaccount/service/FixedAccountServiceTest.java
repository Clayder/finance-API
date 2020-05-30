package com.clayder.financestdd.fixedaccount.service;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.model.repository.IFixedAccountRepository;
import com.clayder.financestdd.api.fixedaccount.service.FixedAccountService;
import com.clayder.financestdd.api.fixedaccount.service.IFixedAccountService;
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
    @DisplayName("Dever salvar uma conta fixa")
    public void saveFixedAccountTest(){

        // cenario
        FixedAccount account = FixedAccount.builder()
				.name("Vivo")
				.owner("Peter")
				.paymentDay(22)
				.price(54.99)
				.build();

        /**
         * Simulando o comportamento do repository
         * Quando salvar a conta returnar a conta de exemplo
         */
        Mockito.when( repository.save(account) ).thenReturn(
                FixedAccount.builder()
                        .id(1)
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
}