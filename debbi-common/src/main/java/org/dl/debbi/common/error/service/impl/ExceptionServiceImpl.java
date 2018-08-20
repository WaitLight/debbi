package org.dl.debbi.common.error.service.impl;

import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.common.error.CommonError;
import org.dl.debbi.common.error.domain.ErrorLog;
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
    private Base64.Encoder encoder;
    @Autowired
    private Base64.Decoder decoder;
    @Autowired
    private Cache<String, ErrorLog> errorLogCache;

    private static final String SEPARATOR = "&";

    @Override
    public String getHash(Exception e) {
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
        String hash = encoder.encodeToString(hashStr.toString().getBytes(StandardCharsets.UTF_8));

        ErrorLog errorLog = new ErrorLog(e.getMessage(), stackInfo, System.currentTimeMillis());
        if (!Objects.isNull(errorLogCache.getIfPresent(hash)))
            log.info("Error hash: {}, errorLog: {}.", hash, errorLog);
        errorLogCache.put(hash, errorLog);

        return hash;
    }

    @Override
    public ErrorLog explainError(String hash) {
        if (StringUtils.isEmpty(hash)) throw CommonError.miss_argument.exception();
        return errorLogCache.getIfPresent(hash);
    }
}
