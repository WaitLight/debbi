package org.dl.debbi.common.error;

// 基础错误类型
public interface ErrorType {
    int getCode();
    String getName();

    default DebbiException exception() {
        return DebbiException.of(this);
    }

    default DebbiException exception(String message) {
        return DebbiException.of(this, message);
    }
}