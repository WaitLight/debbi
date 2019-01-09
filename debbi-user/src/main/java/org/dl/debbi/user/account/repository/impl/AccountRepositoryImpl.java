package org.dl.debbi.user.account.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.user.account.domain.Account;
import org.dl.debbi.user.account.repository.AccountRepository;
import org.dl.debbi.user.account.utils.AccountLogic;
import org.dl.debbi.user.error.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJpaRepository jpa;

    @Override
    @Transactional
    public Account signUp(String username, String password) {
        Account account = Account.signUp(username, password);
        Long id;
        while (!jpa.existsById(id = AccountLogic.randomAccountId())) {
            return jpa.save(account.setId(id));
        }
        throw UserError.REGISTER_FAIL.e();
    }
}