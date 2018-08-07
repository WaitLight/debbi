package org.dl.debbi.common.error;

// 基础错误类型
public interface ErrorType {
    int getCode();
    DebbiException exception();
}