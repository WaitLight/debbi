package org.dl.debbi.user.vo;

import lombok.Data;

/**
 * 用户登录数据接收对象
 *
 * @author Dean
 * @version 0.0.1
 */
@Data
public class LoginVo {
    private String principal;
    private String credential;
}
