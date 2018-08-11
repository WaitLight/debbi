package org.dl.debbi.user;

import org.dl.debbi.common.error.ErrorType;

public enum UserError implements ErrorType {

    invalid_principal(144101),              // 非法用户名
    register_fail(144601),                  // 注册失败
    conflict_principal(140901),             // 用户名重复
    ;

    private int code;

    UserError(int code) {
        this.code = code;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name();
    }
}