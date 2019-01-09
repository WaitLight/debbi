package org.dl.debbi.user.account.service;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.dl.debbi.user.account.domain.Account;

public interface AccountService {
    Account register(String username, String password, String code);

    // 鉴权
    Account auth(String username, String password);

    default UsernamePasswordToken token(String username, String password) {
        Account account = auth(username, password);
        return new UsernamePasswordToken(account.getUsername(), account.getPassword());
    }

    // 重置密码
    void resetPassword(long id, String originalPassword, String newPassword);

    // 修改用户名
    void updateUsername(long id, String username);

    String login(String username, String password);
}
