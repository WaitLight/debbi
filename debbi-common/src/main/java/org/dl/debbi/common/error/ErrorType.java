package org.dl.debbi.common.error;

// 基础错误类型
public interface ErrorType {
    int getCode();
    String getName();

    default DebbiException exception() {
        return new DebbiException(this);
    }

    default DebbiException exception(String message) {
        return new DebbiException(this, message);
    }
}