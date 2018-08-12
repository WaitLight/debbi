package org.dl.debbi.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordService;
import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.user.UserError;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.repo.AccountRepository;
import org.dl.debbi.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public Optional<Account> register(String principal, String certificate) {
        return accountRepo.register(principal, passwordService.encryptPassword(certificate));
    }

    @Override
    public Account auth(String principal, String certificate) {
        Optional<Account> accountOpt = accountRepo.getByPrincipal(principal);
        if (!accountOpt.isPresent()) throw UserError.invalid_principal.exception();

        Account account = accountOpt.get();
        if (passwordService.passwordsMatch(certificate, account.certificate))
            return account;

        throw CommonError.unauthorized.exception();
    }
}
