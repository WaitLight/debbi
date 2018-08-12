package org.dl.debbi.user.web.controller;

import org.dl.debbi.common.error.CommonError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @PostMapping("/login")
    public Object login() {
        return CommonError.todo.exception();
    }

    @PostMapping("/save")
    public Object save() {
        return CommonError.todo.exception();
    }


}
