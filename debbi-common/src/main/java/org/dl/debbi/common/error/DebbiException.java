package org.dl.debbi.common.error;

public class DebbiException extends RuntimeException {

    private ErrorType type;
    private String data;

    public DebbiException() {
    }

    public DebbiException(ErrorType type) {
        super(type.getCode() + " " + type.getName());
        this.type = type;
    }

    public DebbiException(ErrorType type, String data) {
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

    private static Integer getHash(Exception e) {
        for (StackTraceElement element : e.getStackTrace()) {
            // 异常堆栈信息类名：org.dl.debbi开头，必须包含repo、repository、dao、service、api、web、util、facade其中一种，不能包含cglib$$或error
            if (element.getClassName().toLowerCase().matches("^org.dl.debbi((repo|repository|dao|service|api|web|util|facade).)*((?!cglib\\$\\$|error).)*$"))
                return element.hashCode();
        }
        return null;
    }
}