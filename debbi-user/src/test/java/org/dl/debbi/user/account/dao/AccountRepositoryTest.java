package org.dl.debbi.user.account.dao;

import org.dl.debbi.user.account.domain.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepo;

    @Before
    public void before() {
        accountRepo.register("test-4001", "123");
    }

    @After
    public void after() {
        accountRepo.cleanup(4001L);
    }

    @Test(expected = Exception.class)
    public void registerDuplicate() {
        accountRepo.register("test-4001", "123");
    }

    @Test
    public void normalRegister() {
        Account account = accountRepo.register("abc312", "123");

        accountRepo.cleanup(account.id);
    }

    @Test
    public void getPreSetAccount() {
        Optional<Account> preSetAccount = accountRepo.get(2000);
        assert preSetAccount.isPresent();
        Account account = preSetAccount.get();
        assert account.id == 2000;
        assert account.username.startsWith("test-");
        assert account.password.equals("123456");
    }

}
