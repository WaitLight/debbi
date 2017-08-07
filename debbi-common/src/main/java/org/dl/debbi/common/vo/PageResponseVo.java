package org.dl.debbi.common.vo;

/**
 * 公用JSON返回对象接口
 * TODO 方法没有完成
 *
 * @author Dean
 * @version 0.0.1
 */
public interface PageResponseVo<T> extends SimpleResponseVo<T> {

    /**
     * 当前页数
     *
     * @return 页数，从0开始
     */
    int getPageNumber();

    /**
     * 每页容量
     *
     * @return 每页数据容量
     */
    int getPageSize();

    /**
     * 是否是第一页
     *
     * @return true false
     */
    boolean isFirst();

    /**
     * 是否是第一页
     *
     * @return true false
     */
    boolean isLast();
}