package org.dl.debbi.user.error;

import org.dl.debbi.common.error.Error;
import org.dl.debbi.common.error.CommonError;

public enum UserError implements Error {

    INVALID_USERNAME(1001),             // 非法用户名
    INVALID_USER(1002),                 // 非法用户
    INVALID_PASSWORD(1003),             // 错误的密码
    REGISTER_FAIL(1004),                // 注册失败
    CONFLICT_USERNAME(1005),            // 用户名重复
    INVALID_CAPTCHA(1006),              // 错误的验证码
    INVALID_UPDATE_KEY_WORD(1007),      // 错误的修改字段
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
        return name().toLowerCase();
    }
}