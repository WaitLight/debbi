package org.dl.debbi.user.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtil {

    public static final String ACCOUNT_REGEX = "test-[0-9]*";

    public static long extractAccountId(String principal) {
        if (StringUtils.isEmpty(principal) || !principal.matches(ACCOUNT_REGEX))
            return -1L;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(principal);
        if (matcher.find()) {
            return Long.valueOf(matcher.group());
        }
        return -1L;
    }

    public static boolean isPreSetAccount(String principal) {
        long accountId = extractAccountId(principal);
        return accountId >= 2000 && accountId < 3000;
    }

    public static boolean isTestAccount(String principal) {
        long accountId = extractAccountId(principal);
        return accountId >= 3000 && accountId < 5000;
    }
}
