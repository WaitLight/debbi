package org.dl.debbi.common.error;

// 通用的错误类型
public enum CommonError implements ErrorType {
    PermissionDenied(1001);         // 没有权限


    private int code;

    CommonError(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public DebbiException exception() {
        return DebbiException.of(this);
    }
}
