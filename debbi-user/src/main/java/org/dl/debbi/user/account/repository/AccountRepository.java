package org.dl.debbi.user.account.repository;

import org.dl.debbi.user.account.domain.Account;

public interface AccountRepository {
    Account signUp(String username, String password);
}
