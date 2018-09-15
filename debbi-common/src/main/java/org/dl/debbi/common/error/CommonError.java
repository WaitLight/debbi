package org.dl.debbi.common.error;

public enum CommonError implements Error {
    UNKONW(11),                 // 未知错误
    UNAUTHORIZED(12),           // 无法确认用户身份
    FORBIDDEN(13),              // 无权限操作数据或功能
    REQUIRED_ARGUMENT(20),      // 缺失必要数据
    INVALID_ARGUMENT(21),       // 数据格式错误，或者越界
    CONFLICT(22),               // 数据冲突，重复
    NOT_SUPPORT(23),            // 不支持的文件类型、媒体类型或版本
    EXPIRED(24),                // 操作或数据过期
    NOT_FOUND(44),              // 没有找到操作对象，或者操作
    LIMITED(51),                // 用户或对象被限制
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
}
