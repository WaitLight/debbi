package org.dl.debbi.common.error.domain;

import lombok.Data;

@Data
public class ExceptionInfo {
    public String className;
    public String methodName;
    public int lineNumber;
}
