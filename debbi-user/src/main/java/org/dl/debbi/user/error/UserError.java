package org.dl.debbi.user.error;

import org.dl.debbi.common.error.Error;
import org.dl.debbi.common.error.Status;

public enum UserError implements Error {

    INVALID_PRINCIPAL(144101, Status.INVALID_ARGUMENT),         // 非法用户名
    INVALID_USER(144102, Status.INVALID_ARGUMENT),              // 非法用户
    INVALID_CERTIFICATE(144103, Status.INVALID_ARGUMENT),       // 错误的密码
    REGISTER_FAIL(144601, Status.INVALID_ARGUMENT),             // 注册失败
    CONFLICT_PRINCIPAL(140901, Status.CONFLICT),                // 用户名重复
    INVALID_CAPTCHA(144105, Status.INVALID_ARGUMENT),           // 错误的验证码
    INVALID_UPDATE_KEY_WORD(1201, Status.INVALID_ARGUMENT),     // 错误的修改字段
    ;

    private int code;
    private Status status;

    UserError(int code, Status status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public int getStatusCode() {
        return status.code();
    }

    @Override
    public String getStatusName() {
        return status.name().toLowerCase();
    }


}