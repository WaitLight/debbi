//package org.dl.debbi.user.code.impl;
//
//import io.lettuce.core.api.sync.RedisCommands;
//import org.dl.debbi.common.utils.BuildConfig;
//import org.dl.debbi.user.code.CodeService;
//import org.dl.debbi.user.error.UserError;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//@Service
//public class StringCodeService implements CodeService<String, String> {
//
//    @Autowired
//    private RedisCommands<String, String> redis;
//
//    public static final int EXPIRED_TIME = 60;
//
//    @Override
//    public String get(String username) {
//        String code = code();
//        redis.setex(getKey(username), EXPIRED_TIME, code);
//        return code;
//    }
//
//    @Override
//    public void verify(String username, String input) {
//        String key = getKey(username);
//        String cacheCode = redis.get(key);
//        if (StringUtils.isEmpty(cacheCode)) {
//            throw UserError.INVALID_CAPTCHA.exception();
//        }
//        if (cacheCode.equals(input)) {
//            redis.del(key);
//            return;
//        }
//        throw UserError.INVALID_CAPTCHA.exception();
//    }
//
//    private String getKey(String username) {
//        return "code_" + username;
//    }
//
//    private String code() {
//        if (BuildConfig.ENABLE_TEST_CODE) {
//            return BuildConfig.TEST_CODE;
//        } else {
//            return String.valueOf(ThreadLocalRandom.current().ints(100000, 999999));
//        }
//    }
//}
