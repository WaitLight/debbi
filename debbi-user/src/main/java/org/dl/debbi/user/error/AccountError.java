package org.dl.debbi.user.error;

import org.dl.debbi.common.error.DebbiException;
import org.dl.debbi.common.error.ErrorType;

public enum AccountError implements ErrorType {

    InvalidPrincipal(2001),     // 非法用户名
    RegisterFail(2002),         // 册失败
    DuplicatePrincipal(2003)    // 用户名重复
    ;

    private int code;

    AccountError(int code) {
        this.code = code;
    }

    @Override
    public DebbiException exception() {
        return DebbiException.of(this);
    }

    @Override
    public int getCode() {
        return this.code;
    }
}