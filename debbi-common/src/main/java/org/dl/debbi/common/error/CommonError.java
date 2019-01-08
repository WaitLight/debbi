package org.dl.debbi.common.error;

public enum CommonError implements Error {
    UNKONW(1),                  // 未知错误
    UNAUTHORIZED(2),            // 无法确认用户身份
    FORBIDDEN(3),               // 无权限操作数据或功能
    REQUIRED_ARGUMENT(4),       // 缺失必要数据
    INVALID_ARGUMENT(5),        // 数据格式错误
    OUT_OF_RANGE(6),            // 超出范围
    CONFLICT(7),                // 数据冲突，重复
    NOT_SUPPORT(8),             // 不支持的文件类型、媒体类型或版本
    EXPIRED(9),                 // 操作或数据过期
    NOT_FOUND(10),              // 没有找到操作对象，或者操作
    LIMITED(11),                // 用户或对象被限制
    TODO(99),                   // 施工中，请绕行
    ;

    private int code;

    CommonError(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public RuntimeException e() {
        return DebbiException.of(this);
    }
}
