package org.dl.debbi.user.code;

public interface CodeService<C, T> {
    C get(String principal);
    void verify(String principal, T input);
}
