package org.dl.debbi.user.web.controller;

import org.dl.debbi.common.vo.ResponseVo;
import org.dl.debbi.user.vo.LoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 *
 * @author Dean
 * @version 0.0.1
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

//    @Autowired
//    private IUserService userService;

    /**
     * 普通登录
     *
     * @param loginVo {@link LoginVo}
     * @return 用户信息
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(@RequestBody LoginVo loginVo) {
//        UseruserService.login(loginVo.getPrincipal(), loginVo.getCredential());
//        return new ResponseVo<>(true, "成功", null, null);
        return new ResponseVo();
    }
}
