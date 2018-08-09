package org.dl.debbi.user.repo.impl;

import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.error.AccountError;
import org.dl.debbi.user.repo.AccountRepository;
import org.dl.debbi.user.utils.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJPARepository japRepo;

    @Override
    @Transactional
    public Optional<Account> regist(String principal, String certificate) {
        // 预置用户不可注册
        if (AccountUtil.isPreSetAccount(principal))
            throw AccountError.InvalidPrincipal.exception();

        Account account = new Account();
        account.principal = principal;
        account.certificate = certificate;

        return insert(account);
    }

    private Optional<Account> insert(Account account) {
        // 保存时可能是id重复，也可能是principal重复
        for (int i = 0; i < 3; i++) {
            if (AccountUtil.isTestAccount(account.principal)) {
                // 测试账号id不随机
                account.id = AccountUtil.extractAccountId(account.principal);
            } else {
                account.id = ThreadLocalRandom.current().nextLong(5000, Long.MAX_VALUE);
            }
            try {
                japRepo.insert(account.id, account.principal, account.certificate);
                return Optional.of(account);
            } catch (Exception e) {
                if (japRepo.findByPrincipal(account.principal) != null) {
                    log.debug("Duplicate principal: {}", account.principal);
                    throw AccountError.DuplicatePrincipal.exception();
                }
            }
        }

        log.info("Retrying 3 times still fails to register.");
        throw AccountError.RegisterFail.exception();
    }


    @Override
    public void cleanup(long accountId) {
        japRepo.delete(accountId);
    }


}