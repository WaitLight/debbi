package org.dl.debbi.controller.code;

import org.dl.debbi.common.vo.Response;
import org.dl.debbi.user.code.impl.StringCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class CodeController {

    @Autowired
    private StringCodeService stringCodeService;

    @GetMapping("/code")
    public Response execute(@RequestParam("username") String username) {
        return Response.success(stringCodeService.get(username));
    }
}
