package org.dl.debbi.user.account.utils;

import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.user.error.UserError;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

public final class AccountHelper {

    public static final String MOCK_PASSWORD = "123456";
    public static final String USERNAME_PREFIX = "test-";

    // 任意数字
    public static final String NUMBER_REGEX = "\\d+";
    // 测试和预置用户名规则test- 开头 ，后面为任意数字
    public static final String TEST_USERNAME_REGEX = "test-[0-9]*";
    // 用户名规则：中英文数字和下划线，最小4位，最长20位
    public static final String USERNAME_REGEX = "[\\u4e00-\\u9fa5_a-zA-Z0-9]{4,20}";

    /*
     * 根据username生成uid
     * 1. 测试账号以test
     *
     */
    public static Long generateId(String username) {
        if (username.matches(TEST_USERNAME_REGEX)) {
            return ThreadLocalRandom.current().nextLong();
        }
        throw CommonError.INVALID_ARGUMENT.e();
    }

    public static boolean isPreSetAccount(String username) {
        long accountId = generateId(username);
        return isPreSetAccount(accountId);
    }

    public static boolean isTestAccount(String username) {
        long accountId = generateId(username);
        return accountId >= 3000 && accountId < 5000;
    }

    public static void assertUsername(String username) {
        if (username.matches(TEST_USERNAME_REGEX)) {//以test开头
            if (isPreSetAccount(username) || isTestAccount(username)) return;
        } else {
            if (username.matches(USERNAME_REGEX)) return;
        }
        throw UserError.INVALID_USERNAME.e();
    }

    public static void assertPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw UserError.INVALID_PASSWORD.e();
        }
    }

    public static boolean isPreSetAccount(long accountId) {
        return accountId >= 2000 && accountId < 3000;
    }
}
