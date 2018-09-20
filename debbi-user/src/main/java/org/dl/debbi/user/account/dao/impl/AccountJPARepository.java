package org.dl.debbi.user.account.dao.impl;

import org.dl.debbi.user.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountJPARepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    @Modifying
    @Query(value = "insert into account (id,username,password,created) value (:id, :usernmae, :password, now())", nativeQuery = true)
    void insert(@Param("id") long id, @Param("username") String username, @Param("password") String password);

    @Modifying
    @Query(value = "update account set deleted = now() where id = :id", nativeQuery = true)
    void delete(@Param("id") long id);
}