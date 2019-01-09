package org.dl.debbi.user.account.dao;

import org.dl.debbi.user.account.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Account signUp(String username, String password);

    Optional<Account> get(long id);
    Optional<Account> getByUsername(String username);

    void delete(long id);
    Account update(Account account);

    // 彻底清理数据
    void cleanup(long id);

    enum Type {
        USERNAME,
        PASSWORD
    }
}
