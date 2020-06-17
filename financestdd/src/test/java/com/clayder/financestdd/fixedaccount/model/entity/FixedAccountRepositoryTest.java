package com.clayder.financestdd.fixedaccount.model.entity;

import com.clayder.financestdd.api.fixedaccount.model.entity.FixedAccount;
import com.clayder.financestdd.api.fixedaccount.model.repository.IFixedAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class FixedAccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IFixedAccountRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir uma conta na base com o 'name' informado.")
    public void returnTrueWhenNameExists() {
        //cenario
        String name = "vivo";
        FixedAccount account = createNewFixedAccount();

        entityManager.persist(account);

        //execucao
        boolean exists = repository.existsByName(name);

        //verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando não existir uma conta na base com o 'name' informado.")
    public void returnFalseWhenNameExists() {
        //cenario
        String name = "vivo";

        //execucao
        boolean exists = repository.existsByName(name);

        //verificacao
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Deve obter uma conta fixa pelo ID.")
    public void findByIdTest(){

        /**
         * Para retornarmos uma conta fixa pelo ID, primeiro temos que inserir ela na
         * base de dados.
         */
        FixedAccount account = createNewFixedAccount();
        entityManager.persist(account);

        // execução
        Optional<FixedAccount> foundAccount = repository.findById(account.getId());

        // Verifico
        assertThat(foundAccount.isPresent()).isTrue();
    }

    private FixedAccount createNewFixedAccount() {
        return FixedAccount.builder()
                    .name("vivo")
                    .owner("Peter")
                    .paymentDay(22)
                    .price(54.99)
                    .build();
    }
}
