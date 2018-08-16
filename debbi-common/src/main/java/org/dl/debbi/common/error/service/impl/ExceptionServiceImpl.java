package org.dl.debbi.common.error.service.impl;

import com.google.common.cache.Cache;
import org.dl.debbi.common.error.domain.ExceptionInfo;
import org.dl.debbi.common.error.service.ExceptionService;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Callable;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private Base64.Encoder encoder;
    @Autowired
    private Base64.Decoder decoder;
    @Autowired
    private Cache<Integer, ExceptionInfo> exceptionInfoCache;

    private static final String SEPARATOR = "&";

    @Override
    public String getHash(Exception e) {
        StringBuilder hash = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            // 异常堆栈信息类名：org.dl.debbi开头，必须包含repo、repository、dao、service、api、web、utils、facade其中一种，不能包含cglib$$或error
            if (element.getClassName().toLowerCase()
                    .matches("^org.dl.debbi((repo|repository|dao|service|api|web|util|facade).)*((?!cglib\\$\\$|error).)*$")) {
                int hashCode = element.hashCode();
                exceptionInfoCache.put(hashCode, ExceptionInfo.of(element));
                hash.append(SEPARATOR).append(hashCode);
            }
        }
        return encoder.encodeToString(hash.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public List<ExceptionInfo> explainError(String hash) {
        List<ExceptionInfo> result = new ArrayList<>();
        String[] hashStrArr = new String(decoder.decode(hash), StandardCharsets.UTF_8).split(SEPARATOR);
        for (int i = 1; i < hashStrArr.length; i++) {
            result.add(exceptionInfoCache.getIfPresent(Integer.valueOf(hashStrArr[i])));
        }
        return result;
    }
}
