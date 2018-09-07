package org.dl.debbi.user.account.utils;

import org.dl.debbi.user.error.UserError;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountHelper {

    public static final String MOCK_CERTIFICATE = "123456";
    public static final String PRINCIPAL_PREFIX = "test-";

    // 任意数字
    public static final String NUMBER_REGEX = "\\d+";
    // 测试和预置用户名规则test- 开头 ，后面为任意数字
    public static final String TEST_PRINCIPAL_REGEX = "test-[0-9]*";
    // 用户名规则：中英文数字和下划线，最小4位，最长20位
    public static final String PRINCIPAL_REGEX = "[\\u4e00-\\u9fa5_a-zA-Z0-9]{4,20}";


    public static long extractAccountId(String principal) {
        if (StringUtils.isEmpty(principal) || !principal.matches(TEST_PRINCIPAL_REGEX))
            return -1L;

        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(principal);
        if (matcher.find()) {
            return Long.valueOf(matcher.group());
        }
        return -1L;
    }

    public static boolean isPreSetAccount(String principal) {
        long accountId = extractAccountId(principal);
        return isPreSetAccount(accountId);
    }

    public static boolean isTestAccount(String principal) {
        long accountId = extractAccountId(principal);
        return accountId >= 3000 && accountId < 5000;
    }

    public static void assertPrincipal(String principal) {
        if (principal.matches(TEST_PRINCIPAL_REGEX)) {//以test开头
            if (isPreSetAccount(principal) || isTestAccount(principal)) return;
        } else {
            if (principal.matches(PRINCIPAL_REGEX)) return;
        }
        throw UserError.INVALID_PRINCIPAL.exception();
    }

    public static void assertCertificate(String certificate) {
        if (StringUtils.isEmpty(certificate)) {
            throw UserError.INVALID_CERTIFICATE.exception();
        }
    }

    public static boolean isPreSetAccount(long accountId) {
        return accountId >= 2000 && accountId < 3000;
    }
}
