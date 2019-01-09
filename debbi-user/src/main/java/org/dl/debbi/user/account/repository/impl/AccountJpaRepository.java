package org.dl.debbi.user.account.repository.impl;

import org.dl.debbi.user.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}