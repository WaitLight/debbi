package org.dl.debbi.controller.account;

import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.user.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dl.debbi.common.vo.Response;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Response register(String username, String password, String code) {
        return Response.success(accountService.register(username, password, code));
    }

    @PostMapping("/login")
    public Response login(String username, String password) {
        accountService.login(username, password);
        return Response.fail(CommonError.TODO);
    }
}
