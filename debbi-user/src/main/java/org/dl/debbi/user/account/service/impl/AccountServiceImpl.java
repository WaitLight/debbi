package org.dl.debbi.user.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.user.account.utils.AccountHelper;
import org.dl.debbi.user.error.UserError;
import org.dl.debbi.user.account.domain.Account;
import org.dl.debbi.user.account.dao.AccountRepository;
import org.dl.debbi.user.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.dl.debbi.user.account.utils.AccountHelper.MOCK_CERTIFICATE;
import static org.dl.debbi.user.account.utils.AccountHelper.isPreSetAccount;

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
        if ((isPreSetAccount(account.id) && MOCK_CERTIFICATE.equals(certificate))
                || passwordService.passwordsMatch(certificate, account.certificate))
            return account;

        throw CommonError.unauthorized.exception();
    }

    @Override
    @Transactional
    public void resetCertificate(long id, String originalCertificate, String newCertificate) {
        // TODO 退出当前登录的所有客户端
        // TODO 验证码
        Account account = transitory(id);
        if (passwordService.passwordsMatch(originalCertificate, account.certificate)) {
            account.certificate = passwordService.encryptPassword(newCertificate);
            accountRepo.update(account);
        } else {
            throw UserError.invalid_certificate.exception();
        }
    }

    @Override
    @Transactional
    public void updatePrincipal(long id, String principal) {
        // TODO 修改用户名 有频率限制，次数限制
        Account account = transitory(id);
        AccountHelper.assertPrincipal(principal);
        account.principal = principal;
        accountRepo.update(account);
    }

    private Account transitory(long id) {
        if (AccountHelper.isPreSetAccount(id)) {
            throw UserError.invalid_user.exception();
        } else {
            Account account = new Account();
            account.id = id;
            return account;
        }
    }
}
