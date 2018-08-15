package org.dl.debbi.common.error;

import java.util.Base64;

public class DebbiException extends RuntimeException {

    private ErrorType type;
    private Object data;

    public DebbiException() {
    }

    public DebbiException(ErrorType type) {
        super(type.getCode() + " " + type.getName());
        this.type = type;
    }

    public DebbiException(ErrorType type, Object data) {
        super(type.getCode() + " " + type.getName());
        this.type = type;
        this.data = data;
    }

    public static DebbiException of(ErrorType errorType) {
        return new DebbiException(errorType);
    }

    public static DebbiException of(ErrorType errorType, String data) {
        return new DebbiException(errorType, data);
    }

    public String getHash() {
        StringBuilder hash = new StringBuilder();
        for (StackTraceElement element : this.getStackTrace()) {
            // 异常堆栈信息类名：org.dl.debbi开头，必须包含repo、repository、dao、service、api、web、util、facade其中一种，不能包含cglib$$或error
            if (element.getClassName().toLowerCase().matches("^org.dl.debbi((repo|repository|dao|service|api|web|util|facade).)*((?!cglib\\$\\$|error).)*$"))
                hash.append("$").append(element.hashCode());
        }
        return Base64.getEncoder().encodeToString(hash.toString().getBytes());
    }

    public String getErr() {
        return type.getName();
    }

    public int getCode() {
        return type.getCode();
    }

    public Object getData() {
        return this.data;
    }
}