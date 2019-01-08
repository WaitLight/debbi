package org.dl.debbi.common.error.service.impl;

import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.common.error.domain.CommonError;
import org.dl.debbi.common.error.domain.DebbiError;
import org.dl.debbi.common.error.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private Cache<String, DebbiError> errorLogCache;

    private Base64.Encoder encoder = Base64.getEncoder();
    private static final String SEPARATOR = "&";

    @Override
    public String getErrorHash(Exception e) {
        StringBuilder hashStr = new StringBuilder();
        List<String> stackInfo = new ArrayList<>();
        for (StackTraceElement element : e.getStackTrace()) {
            // 异常堆栈信息类名：org.dl.debbi开头，必须包含repo、repository、dao、service、api、web、utils、facade其中一种，不能包含cglib$$或error
            if (element.getClassName().toLowerCase()
                    .matches("^org.dl.debbi((repo|repository|dao|service|api|web|util|facade).)*((?!cglib\\$\\$|error).)*$")) {
                hashStr.append(SEPARATOR).append(element.hashCode());
                stackInfo.add(element.getClassName() + "." + element.getMethodName() + ": " + element.getLineNumber());
            }
        }
        String errorHash = encoder.encodeToString(hashStr.toString().getBytes(StandardCharsets.UTF_8));
        DebbiError errorLog = new DebbiError(e.getMessage(), stackInfo, System.currentTimeMillis());
        if (!Objects.isNull(errorLogCache.getIfPresent(errorHash)))
            log.info("ErrorHash: {}, DebbiError: {}.", errorHash, errorLog);
        errorLogCache.put(errorHash, errorLog);

        return errorHash;
    }

    @Override
    public DebbiError explainError(String errorHash) {
        if (StringUtils.isEmpty(errorHash)) throw CommonError.INVALID_ARGUMENT.exception();
        return errorLogCache.getIfPresent(errorHash);
    }
}
