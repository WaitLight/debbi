package org.dl.debbi.user.error;

import org.dl.debbi.common.error.ErrorType;

public enum UserError implements ErrorType {

    invalid_principal(144101),              // 非法用户名
    invalid_user(144102),                   // 非法用户
    invalid_certificate(144103),            // 错误的密码
    register_fail(144601),                  // 注册失败
    conflict_principal(140901),             // 用户名重复
    invalid_user_info(144104),              // 错误的用户信息参数
    invalid_captcha(144105)                 // 错误的验证码
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