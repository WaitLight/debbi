package org.dl.debbi.common.error;

public final class DebbiException extends RuntimeException {

    private ErrorType type;
    private Object data;

    private DebbiException(ErrorType type) {
        super(type.getCode() + " " + type.getName());
        this.type = type;
    }

    private DebbiException(ErrorType type, Object data) {
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