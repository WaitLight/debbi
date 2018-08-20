package org.dl.debbi.user.code;

public interface CodeService {
    String get(long userId);
    boolean verify(long userId, String code);
}
