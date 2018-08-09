package org.dl.debbi.user.service.impl;

import org.apache.shiro.authc.credential.PasswordService;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.repo.AccountRepository;
import org.dl.debbi.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordService passwordService;

    @Override
    public Optional<Account> register(String principal, String certificate) {
        return accountRepo.regist(principal, passwordService.encryptPassword(certificate));
    }
}
