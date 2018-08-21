package org.dl.debbi.user.code;

public interface CodeService<C, T> {
    C get(long userId);
    boolean verify(long userId, T input);
}
