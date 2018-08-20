package org.dl.debbi.controller.error;

import org.dl.debbi.common.error.service.ExceptionService;
import org.dl.debbi.common.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error")
public class ErrorController {
    @Autowired
    private ExceptionService exceptionService;

    @GetMapping("/explain")
    public Response explain(String hash) {
        return Response.succ(exceptionService.explainError(hash));
    }
}
