package org.dl.debbi.user.account.utils;

import org.junit.Test;

public class UKeyWorkerTest {

    @Test
    public void x1() {
        SnowFlake snowFlake = new SnowFlake(1L, 2L);
        for (int i = 0; i < 20; i++) {
            System.out.println(snowFlake.nextId());

        }
    }

    @Test
    public void x2() {
        int x = ~(-1 << 12);
        for (int i = 1; i <= x + 1; i++) {
            System.out.println(i + " & " + x + " = " + (i & x));
        }
    }

}