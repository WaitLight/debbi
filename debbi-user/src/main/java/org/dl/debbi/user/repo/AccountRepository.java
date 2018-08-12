package org.dl.debbi.user.repo;

import org.dl.debbi.user.domain.Account;

import java.lang.reflect.Field;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> register(String principal, String certificate);

    Optional<Account> get(long id);
    Optional<Account> getByPrincipal(String principal);

    void delete(long id);
    Optional<Account> update(long id, String field, Object value);

    // 彻底清理数据
    void cleanup(long id);
}
