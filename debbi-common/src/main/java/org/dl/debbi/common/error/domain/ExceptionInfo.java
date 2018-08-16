package org.dl.debbi.common.error.domain;

import lombok.Data;

@Data
public class ExceptionInfo {
    public String className;
    public String methodName;
    public int lineNumber;
    public long timeStamp;

    public static ExceptionInfo of(StackTraceElement element) {
        ExceptionInfo info = new ExceptionInfo();
        info.className = element.getClassName();
        info.methodName = element.getMethodName();
        info.lineNumber = element.getLineNumber();
        info.timeStamp = System.currentTimeMillis();
        return info;
    }
}
