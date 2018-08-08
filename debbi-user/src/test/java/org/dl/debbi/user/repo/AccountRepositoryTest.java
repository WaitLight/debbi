package org.dl.debbi.user.repo;

import org.dl.debbi.user.domain.Account;
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
        Optional<Account> accountOpt = accountRepo.regist("test-4001", "123");
        assert accountOpt.isPresent();
    }

    @After
    public void after() {
        accountRepo.cleanup(4001L);
    }

    @Test(expected = Exception.class)
    public void registerDuplicate() {
        accountRepo.regist("test-4001", "123");
    }

    @Test
    public void normalRegister() {
        Optional<Account> accountOptional = accountRepo.regist("abc312", "123");
        assert accountOptional.isPresent();

        accountRepo.cleanup(accountOptional.get().id);
    }

}
