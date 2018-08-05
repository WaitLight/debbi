package org.dl.debbi.user.repo;

import org.dl.debbi.user.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> regist(String principal, String certificate);

    // 彻底清理数据
    void cleanup(long accountId);
}
