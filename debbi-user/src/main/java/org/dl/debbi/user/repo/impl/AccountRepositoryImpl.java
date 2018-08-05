package org.dl.debbi.user.repo.impl;

import org.dl.debbi.common.error.DebbiException;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.repo.AccountRepository;
import org.dl.debbi.user.utils.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJPARepository japRepo;

    @Override
    public Optional<Account> regist(String principal, String certificate) {
        // 预置用户不可注册
        if (AccountUtil.isPreSetAccount(principal))
            throw DebbiException.of("invalid_account");

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

    private Optional<Account> save(Account account) {
        // 保存时可能是id重复，也可能是principal重复
        try {
            account = japRepo.save(account);
        } catch (Exception e) {

        }


        return Optional.of(account);
    }


    @Override
    public void cleanup(long accountId) {
        japRepo.delete(accountId);
    }


}