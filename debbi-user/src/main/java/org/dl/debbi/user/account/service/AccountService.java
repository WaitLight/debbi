package org.dl.debbi.user.account.service;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.dl.debbi.user.account.domain.Account;

import java.util.Optional;

public interface AccountService {
    Account register(String principal, String certificate, String code);

    // 鉴权
    Account auth(String principal, String certificate);

    default UsernamePasswordToken token(String principal, String certificate) {
        Account account = auth(principal, certificate);
        return new UsernamePasswordToken(account.principal, account.certificate);
    }

    // 重置密码
    void resetCertificate(long id, String originalCertificate, String newCertificate);

    // 修改用户名
    void updatePrincipal(long id, String principal);
}
