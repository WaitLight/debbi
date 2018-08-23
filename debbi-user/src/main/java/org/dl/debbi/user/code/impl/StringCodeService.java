package org.dl.debbi.user.code.impl;

import io.lettuce.core.api.sync.RedisCommands;
import org.dl.debbi.common.utils.TestHelper;
import org.dl.debbi.user.code.CodeService;
import org.dl.debbi.user.error.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class StringCodeService implements CodeService<String, String> {

    @Autowired
    private RedisCommands<String, String> redis;

    public static final int EXPIRED_TIME = 60;

    @Override
    public String get(String principal) {
        String code = code();
        redis.setex(getKey(principal), EXPIRED_TIME, code);
        return code;
    }

    @Override
    public void verify(String principal, String input) {
        String key = getKey(principal);
        String cacheCode = redis.get(key);
        if (StringUtils.isEmpty(cacheCode)) {
            throw UserError.invalid_captcha.exception();
        }
        if (cacheCode.equals(input)) {
            redis.del(key);
            return;
        }
        throw UserError.invalid_captcha.exception();
    }

    private String getKey(String principal) {
        return "code_" + principal;
    }

    private String code() {
        if (TestHelper.ENABLE_TEST_CODE) {
            return TestHelper.TEST_CODE;
        } else {
            return String.valueOf(ThreadLocalRandom.current().ints(100000, 999999));
        }
    }
}
