package org.dl.debbi.common.error;

// 系统级别错误
public enum SystemError implements ErrorType {
    miss_protoco(144201),                   //缺失协议参数
    unsupport_protocol(141501),             //请求的协议不支持，或要求响应的协议不支持
    miss_app_version(144202),               //缺失app版本号
    unsupport_app_version(141502),          //不支持的app版本
    miss_timestamp(144203),                 //缺失时间戳
    invalid_timestamp(144101),              //非法时间戳
    not_found_cmd(140401),                  //命令没有找到
    miss_sign(144201),                      //缺失签名，例如接口权限签名缺失
    invalid_sign(144102),                   //签名错误，例如接口权限签名缺失

    ;
    private int code;

    SystemError(int code) {
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
