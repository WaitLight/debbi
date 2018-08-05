package org.dl.debbi.user.repo;

import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.repo.impl.AccountJPARepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback(false)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepo;

    private long accountId;

    @Before
    public void before() {
        Optional<Account> accountOpt = accountRepo.regist("test-101", "123");
        assert accountOpt.isPresent();
        accountId = accountOpt.get().id;
    }

}
