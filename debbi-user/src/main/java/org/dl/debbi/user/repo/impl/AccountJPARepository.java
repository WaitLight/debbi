package org.dl.debbi.user.repo.impl;

import org.dl.debbi.user.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJPARepository extends JpaRepository<Account, Long> {

    Account insert(Account account);
}
