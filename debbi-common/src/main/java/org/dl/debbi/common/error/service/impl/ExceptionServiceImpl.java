package org.dl.debbi.common.error.service.impl;

import org.dl.debbi.common.error.service.ExceptionService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public String getHash(Exception e) {
        StringBuilder hash = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            // 异常堆栈信息类名：org.dl.debbi开头，必须包含repo、repository、dao、service、api、web、util、facade其中一种，不能包含cglib$$或error
            if (element.getClassName().toLowerCase().matches("^org.dl.debbi((repo|repository|dao|service|api|web|util|facade).)*((?!cglib\\$\\$|error).)*$"))
                hash.append("$").append(element.hashCode());
        }
        return Base64.getEncoder().encodeToString(hash.toString().getBytes());
    }

    @Override
    public Object resolveHash(String hash) {
        return null;
    }
}
