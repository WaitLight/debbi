package org.dl.debbi.common.vo;

/**
 * 公用JSON返回对象接口
 *
 * @author Dean
 * @version 0.0.1
 */
public interface SimpleResponseVo<T> {

    /**
     * 获取返回对象的状态
     *
     * @return 后台系统正常返回就是true，出现错误是返回false，
     * 返回数据是布尔类型时，可以使用status进行返回
     */
    Boolean getStatus();

    /**
     * 提示信息
     *
     * @return 成功时可以为null, 错误时需要有提示信息
     */
    String getMessage();

    /**
     * 状态码或错误码
     *
     * @return 用于定位详细错误，或提示前端操作
     */
    String getCode();

    /**
     * 返回的数据
     *
     * @return 数据
     */
    T getContent();
}