package org.dl.debbi.user.account.dao.impl;

import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.common.utils.BuildConfig;
import org.dl.debbi.user.account.utils.AccountHelper;
import org.dl.debbi.user.error.UserError;
import org.dl.debbi.user.account.domain.Account;
import org.dl.debbi.user.account.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.dl.debbi.user.account.utils.AccountHelper.*;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountJPARepository jpaRepo;

    @Autowired
    private LoadingCache<Long, Account> accountCache;

    @Override
    @Transactional
    public Account register(String principal, String certificate) {
        assertPrincipal(principal);
        assertCertificate(certificate);

        Account account = new Account();
        account.principal = principal;
        account.certificate = certificate;
        return insert(account);
    }

    @Override
    public Optional<Account> get(long id) {
        if (isPreSetAccount(id)) return Optional.ofNullable(getMock(id));
        return jpaRepo.findById(id).filter(Account::isDeleted);
    }

    private Account getMock(Long id) {
        if (!BuildConfig.ENABLE_PRESET_USER) return null;
        return accountCache.getUnchecked(id);
    }

    @Override
    public Optional<Account> getByPrincipal(String principal) {
        if (isPreSetAccount(principal))
            return Optional.ofNullable(getMock(extractAccountId(principal)));

        return jpaRepo.findByPrincipal(principal);
    }

    @Override
    public void delete(long id) {
        if (isPreSetAccount(id))
            throw UserError.INVALID_USER.exception();
        jpaRepo.delete(id);
    }

    @Transactional
    public synchronized Optional<Account> update(long id, Type type, Object value) {
        // TODO: 需要同步整个方法吗
        if (isPreSetAccount(id)) throw UserError.INVALID_USER.exception();
        Optional<Account> accountOpt = get(id);
        if (!accountOpt.isPresent()) throw UserError.INVALID_USER.exception();

        Account account = accountOpt.get();

        switch (type) {
            case PRINCIPAL:
                account.principal = String.valueOf(value);
                AccountHelper.assertPrincipal(account.principal);
                break;
            case CERTIFICATE:
                account.certificate = String.valueOf(value);
                break;
            default:
                throw UserError.INVALID_UPDATE_KEY_WORD.exception();
        }
        return Optional.of(jpaRepo.save(account));
    }

    @Override
    @Transactional
    public synchronized Account update(Account account) {
        if (jpaRepo.existsById(account.id))
            return jpaRepo.save(account);
        else
            throw UserError.INVALID_USER.exception();
    }

    private Account insert(Account account) {
        // 保存时可能是id重复，也可能是principal重复
        for (int i = 0; i < 3; i++) {
            if (isTestAccount(account.principal)) {
                account.id = extractAccountId(account.principal);// 测试账号id不随机
            } else {
                account.id = ThreadLocalRandom.current().nextLong(5000, Long.MAX_VALUE);
            }
            try {
                jpaRepo.insert(account.id, account.principal, account.certificate);
                return account;
            } catch (Exception e) {
                if (jpaRepo.findByPrincipal(account.principal).isPresent()) {
                    log.debug("Duplicate principal: {}", account.principal);
                    throw UserError.CONFLICT_PRINCIPAL.exception();
                }
            }
        }

        log.info("Retrying 3 times still fails to register.");
        throw UserError.REGISTER_FAIL.exception();
    }

    @Override
    public void cleanup(long id) {
        jpaRepo.deleteById(id);
    }
}