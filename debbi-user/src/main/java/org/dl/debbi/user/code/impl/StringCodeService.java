package org.dl.debbi.user.code.impl;

import io.lettuce.core.api.sync.RedisCommands;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.dl.debbi.common.utils.TestHelper;
import org.dl.debbi.user.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StringCodeService implements CodeService<String, String> {

    @Autowired
    private RedisCommands<String, String> redis;

    public static final int EXPIRED_TIME = 60;

    @Override
    public String get(long userId) {
        String code = code();
        redis.setex(getKey(userId), EXPIRED_TIME, code);
        return code;
    }

    @Override
    public boolean verify(long userId, String input) {
        String key = getKey(userId);
        String cacheCode = redis.get(key);
        if (StringUtils.isEmpty(cacheCode)) {
            return false;
        }
        if (cacheCode.equals(input)) {
            redis.del(key);
            return true;
        }
        return false;
    }

    private String getKey(long userId) {
        return "code_" + userId;
    }

    private String code() {
        if (TestHelper.ENABLE_TEST_CODE) {
            return TestHelper.TEST_CODE;
        } else {
            return "666666";
        }
    }
}
