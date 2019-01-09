package org.dl.debbi.user.account.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class AccountLogic {

    // 测试账号ID范围: [1,999]
    public static long MIN_TEST_ACCOUNT_ID = 1L;
    public static long MAX_TEST_ACCOUNT_ID = 999L;

    public static long MIN_ACCOUNT_ID = 1000L;
    public static long MAX_ACCOUNT_ID = Long.MAX_VALUE;


    public static long randomTestAccountId() {
        return ThreadLocalRandom.current().nextLong(MIN_TEST_ACCOUNT_ID, MAX_TEST_ACCOUNT_ID);
    }

    public static long randomAccountId() {
        return ThreadLocalRandom.current().nextLong(MIN_ACCOUNT_ID, MAX_ACCOUNT_ID);
    }

}
