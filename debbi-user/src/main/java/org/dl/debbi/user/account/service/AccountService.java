package org.dl.debbi.user.account.service;

import org.dl.debbi.user.account.domain.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> register(String principal, String certificate);

    // 授权
    Account auth(String principal, String certificate);

    // 重置密码
    void resetCertificate(long id, String originalCertificate, String newCertificate);

    // 修改用户名
    void updatePrincipal(long id, String principal);
}
