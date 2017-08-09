package org.dl.debbi.user.service;

import org.dl.debbi.user.domain.Authority;
import org.dl.debbi.user.vo.LoginVo;

/**
 * 授权信息服务
 *
 * @author Dean
 * @version 0.0.1
 */
public interface IAuthorityService {
    /**
     * 新增
     *
     * @param authority
     * @return
     */
    Authority save(Authority authority);

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    Boolean login(LoginVo loginVo);
}
