package org.dl.debbi.common.error;

// 通用的错误类型
public enum CommonError implements ErrorType {
    unauthorized(401),                  // 权限认证失败
    forbidden(403),                     // 权限认真成功，但当前用户无权使用此项功能
    permission_denied(405),             // 没有权限

    not_found(404),                     // 没找到操作对象
    conflict(409),                      // 操作对象重复
    invalid_argument(441),              // 参数类型错误，或越过边界
    miss_argument(442),                 // 缺少必填参数

    unsupport(415),                     // 不支持操作或数据类型
    expired(444),                       // 过期，数据的状态已经变更，且不能变回去的状态
    status(445),                        // 当前操作的目标状态不允许操作
    fail(446),                          // 当前操作可以允许失败时的错误
    todo(503),                          // 施工中请绕行
    server_error(500),                  // 未知异常
    ;


    private int code;

    CommonError(int code) {
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
