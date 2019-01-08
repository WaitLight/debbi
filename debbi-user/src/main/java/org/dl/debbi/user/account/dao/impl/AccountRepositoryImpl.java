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
    public Account register(String username, String password) {
        assertUsername(username);
        assertPassword(password);

        Account account = new Account();
        account.username = username;
        account.password = password;
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
    public Optional<Account> getByUsername(String username) {
        if (isPreSetAccount(username))
            return Optional.ofNullable(getMock(extractAccountId(username)));

        return jpaRepo.findByUsername(username);
    }

    @Override
    public void delete(long id) {
        if (isPreSetAccount(id))
            throw UserError.INVALID_USER.exception();
//        jpaRepo.delete(id);
    }

    @Transactional
    public synchronized Optional<Account> update(long id, Type type, Object value) {
        // TODO: 需要同步整个方法吗
        if (isPreSetAccount(id)) throw UserError.INVALID_USER.exception();
        Optional<Account> accountOpt = get(id);
        if (!accountOpt.isPresent()) throw UserError.INVALID_USER.exception();

        Account account = accountOpt.get();

        switch (type) {
            case USERNAME:
                account.username = String.valueOf(value);
                AccountHelper.assertUsername(account.username);
                break;
            case PASSWORD:
                account.password = String.valueOf(value);
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
        // 保存时可能是id重复，也可能是username重复
        for (int i = 0; i < 3; i++) {
            if (isTestAccount(account.username)) {
                account.id = extractAccountId(account.username);// 测试账号id不随机
            } else {
                account.id = ThreadLocalRandom.current().nextLong(5000, Long.MAX_VALUE);
            }
            try {
//                jpaRepo.insert(account.id, account.username, account.password);
                return account;
            } catch (Exception e) {
                if (jpaRepo.findByUsername(account.username).isPresent()) {
                    log.debug("Duplicate username: {}", account.username);
                    throw UserError.CONFLICT_USERNAME.exception();
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