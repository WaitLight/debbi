package org.dl.debbi.user.repo.impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import org.dl.debbi.user.domain.Account;
import org.dl.debbi.user.UserError;
import org.dl.debbi.user.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.dl.debbi.user.utils.AccountHelper.assertPrincipal;
import static org.dl.debbi.user.utils.AccountHelper.isTestAccount;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJPARepository jpaRepo;

    @Override
    @Transactional
    public Optional<Account> register(String principal, String certificate) {

        assertPrincipal(principal);

        Account account = new Account();
        account.principal = principal;
        account.certificate = certificate;

        return insert(account);
    }

    @Override
    public Optional<Account> get(long id) {
        return jpaRepo.findById(id).filter(Account::isDeleted);
    }

    @Override
    public Optional<Account> getByPrincipal(String principal) {
        return jpaRepo.findByPrincipal(principal);
    }

    @Override
    public void delete(long id) {
        // TODO: 补充其他数据的联动
        jpaRepo.delete(id);
    }

    @Override
    @Transactional
    public synchronized Optional<Account> update(long id, String field, Object value) {
        // TODO: 需要同步整个方法吗
        Optional<Account> accountOpt = get(id);
        if (!accountOpt.isPresent()) throw UserError.invalid_user.exception();

        Account account = accountOpt.get();
        try {
            Field f = Account.class.getDeclaredField(field);
            f.setAccessible(true);
            f.set(account, value);
        } catch (NoSuchFieldException e) {
            throw UserError.invalid_user_info.exception("Field not exists: " + field);
        } catch (IllegalAccessException e) {
            throw UserError.invalid_user_info.exception("Set value fail, value: " + value);
        }

        return Optional.of(jpaRepo.save(account));
    }

    private Optional<Account> insert(Account account) {
        // 保存时可能是id重复，也可能是principal重复
        for (int i = 0; i < 3; i++) {
            long testAccountId = isTestAccount(account.principal);
            if (testAccountId != -1L) {
                account.id = testAccountId;// 测试账号id不随机
            } else {
                account.id = ThreadLocalRandom.current().nextLong(5000, Long.MAX_VALUE);
            }
            try {
                jpaRepo.insert(account.id, account.principal, account.certificate);
                return Optional.of(account);
            } catch (Exception e) {
                if (jpaRepo.findByPrincipal(account.principal).isPresent()) {
                    log.debug("Duplicate principal: {}", account.principal);
                    throw UserError.conflict_principal.exception("Principal: " + account.principal + " conflict!");
                }
            }
        }

        log.info("Retrying 3 times still fails to register.");
        throw UserError.register_fail.exception("Registration failed, please try again!");
    }


    @Override
    public void cleanup(long id) {
        jpaRepo.deleteById(id);
    }


}