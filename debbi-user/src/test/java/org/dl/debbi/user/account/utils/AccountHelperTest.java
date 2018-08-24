package org.dl.debbi.user.account.utils;

import org.dl.debbi.common.error.DebbiException;
import org.junit.Test;

import static org.dl.debbi.user.account.utils.AccountHelper.assertPrincipal;
import static org.dl.debbi.user.account.utils.AccountHelper.isPreSetAccount;
import static org.dl.debbi.user.account.utils.AccountHelper.isTestAccount;
import static org.junit.Assert.*;

public class AccountHelperTest {

    @Test
    public void isPreSetAccountTest() {
        assertTrue(isPreSetAccount("test-2001"));
        assertFalse(isPreSetAccount("test-3001"));
        assertFalse(isPreSetAccount("test-1999"));
        assertTrue(isPreSetAccount(2001L));
    }

    @Test
    public void isTestAccountTest() {
        assertFalse(isTestAccount("test-101"));
        assertFalse(isTestAccount("test-1001"));
        assertTrue(isTestAccount("test-3001"));
        assertTrue(isTestAccount("test-4999"));
    }

    @Test(expected = DebbiException.class)
    public void assertPrincipalTest__Error_Test() {
        assertPrincipal("test-101");
    }

    @Test(expected = DebbiException.class)
    public void assertPrincipalTest__Error() {
        assertPrincipal("fsad&^");
    }

    @Test
    public void assertPrincipalTest() {
        assertPrincipal("fdasf123");
        assertPrincipal("test-2001");
        assertPrincipal("test-3001");
    }
}
