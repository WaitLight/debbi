package org.dl.debbi.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 公用JSON返回对象
 *
 * @author Dean
 * @version 0.0.1
 */
@Data
public abstract class ResponseVo<T> implements SimpleResponseVo<T>, Serializable {

    private static final long serialVersionUID = 6973678603919974923L;

    //状态 true|false
    private Boolean status;

    //提示信息
    private String message;

    //提示代码，一般用于错误排查
    private String code;

    //返回的实际内容
    private T content;

    public ResponseVo() {
    }

    public ResponseVo(Boolean status, String message, String code, T content) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.content = content;
    }
}