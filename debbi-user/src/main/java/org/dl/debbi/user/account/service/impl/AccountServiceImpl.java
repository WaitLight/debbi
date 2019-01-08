package org.dl.debbi.user.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordService;
import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.user.account.dao.AccountRepository;
import org.dl.debbi.user.account.domain.Account;
import org.dl.debbi.user.account.service.AccountService;
import org.dl.debbi.user.account.utils.AccountHelper;
import org.dl.debbi.user.error.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.dl.debbi.user.account.utils.AccountHelper.MOCK_PASSWORD;
import static org.dl.debbi.user.account.utils.AccountHelper.isPreSetAccount;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordService passwordService;
//    @Autowired
//    private StringCodeService codeService;

    @Override
    @Transactional
    public synchronized Account register(String username, String password, String code) {
//        codeService.verify(username, code);
        return accountRepo.register(username, passwordService.encryptPassword(password));
    }

    @Override
    public Account auth(String username, String password) {
        Optional<Account> accountOpt = accountRepo.getByUsername(username);
        if (!accountOpt.isPresent()) throw UserError.INVALID_USERNAME.exception();

        Account account = accountOpt.get();
        if ((isPreSetAccount(account.id) && MOCK_PASSWORD.equals(password))
                || passwordService.passwordsMatch(password, account.password))
            return account;

        throw CommonError.UNAUTHORIZED.exception();
    }

    @Override
    @Transactional
    public void resetPassword(long id, String originalPassword, String newPassword) {
        // TODO 退出当前登录的所有客户端
        // TODO 验证码
        Account account = transitory(id);
        if (passwordService.passwordsMatch(originalPassword, account.password)) {
            account.password = passwordService.encryptPassword(newPassword);
            accountRepo.update(account);
        } else {
            throw UserError.INVALID_PASSWORD.exception();
        }
    }

    @Override
    @Transactional
    public void updateUsername(long id, String username) {
        // TODO 修改用户名 有频率限制，次数限制
        Account account = transitory(id);
        AccountHelper.assertUsername(username);
        account.username = username;
        accountRepo.update(account);
    }

    @Override
    public String login(String username, String password) {
        Account account = accountRepo.getByUsername(username).orElseThrow(UserError.INVALID_USER::exception);
        if (passwordService.passwordsMatch(password, account.password)) {

        } else {
            throw UserError.INVALID_USER.exception();
        }
        return null;
    }

    private Account transitory(long id) {
        if (AccountHelper.isPreSetAccount(id)) {
            throw UserError.INVALID_USER.exception();
        } else {
            Account account = new Account();
            account.id = id;
            return account;
        }
    }
}
