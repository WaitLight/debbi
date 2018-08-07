package org.dl.debbi.user.repo.impl;

import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.error.AccountError;
import org.dl.debbi.user.repo.AccountRepository;
import org.dl.debbi.user.utils.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJPARepository japRepo;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Account> regist(String principal, String certificate) {
        // 预置用户不可注册
        if (AccountUtil.isPreSetAccount(principal))
            throw AccountError.InvalidPrincipal.exception();

        Account account = new Account();
        account.principal = principal;
        account.certificate = certificate;
        account.created = new Date();

        // 测试账号id不随机
        if (AccountUtil.isTestAccount(principal)) {
            account.id = AccountUtil.extractAccountId(principal);
        } else {

        }

        return Optional.empty();
    }

    private Optional<Account> insert(Account account) {
        // 保存时可能是id重复，也可能是principal重复
        for (int i = 0; i < 3; i++) {
            account.created = new Date();
            account.id = ThreadLocalRandom.current().nextLong(5000, Long.MAX_VALUE);
            try {
                // TODO: insert
            } catch (Exception e) {
                if (japRepo.findByPrincipal(account.principal) != null)
                    throw AccountError.DuplicatePrincipal.exception();
            }
            return Optional.of(account);
        }

        log.info("Retrying 3 times still fails to register.");
        throw AccountError.RegisterFail.exception();
    }


    @Override
    public void cleanup(long accountId) {
        japRepo.delete(accountId);
    }


}