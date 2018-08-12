package org.dl.debbi.user.service;

import org.dl.debbi.user.domain.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> register(String principal, String certificate);

    //授权
    Account auth(String principal, String certificate);
}
