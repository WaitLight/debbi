package org.dl.debbi.user.web.controller;

import org.dl.debbi.user.domain.Authority;
import org.dl.debbi.user.service.IAuthorityService;
import org.dl.debbi.user.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Dean
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IAuthorityService authorityService;


    /**
     * 普通登录
     *
     * @param loginVo {@link LoginVo}
     * @return 用户信息
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginVo loginVo) {
        return authorityService.login(loginVo);
    }

    @PostMapping("/save")
    public Object save(@RequestBody Authority authority) {
        return authorityService.save(authority);
    }


}
