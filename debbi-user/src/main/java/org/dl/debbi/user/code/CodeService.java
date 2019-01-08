package org.dl.debbi.user.code;

public interface CodeService<C, T> {
    C get(String username);
    void verify(String username, T input);
}
