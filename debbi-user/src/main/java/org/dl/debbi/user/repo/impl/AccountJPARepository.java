package org.dl.debbi.user.repo.impl;

import org.dl.debbi.user.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountJPARepository extends JpaRepository<Account, Long> {

    Account findByPrincipal(String principal);

    @Modifying
    @Query(value = "insert into account (id,principal,certificate,created) value (:id, :principal, :certificate, now())", nativeQuery = true)
    void insert(@Param("id") long id, @Param("principal") String principal, @Param("certificate") String certificate);
}