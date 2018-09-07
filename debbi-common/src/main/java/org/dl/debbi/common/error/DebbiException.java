package org.dl.debbi.common.error;

public final class DebbiException extends RuntimeException {

    private Error type;
    private Object data;

    private DebbiException(Error type) {
        super(type.code() + " " + type.name());
        this.type = type;
    }

    private DebbiException(Error type, Object data) {
        super(type.code() + " " + type.name());
        this.type = type;
        this.data = data;
    }

    public static DebbiException of(Error errorType) {
        return new DebbiException(errorType);
    }

    public static DebbiException of(Error errorType, String data) {
        return new DebbiException(errorType, data);
    }

    public String getErr() {
        return type.name();
    }

    public int getCode() {
        return type.code();
    }

    public Object getData() {
        return this.data;
    }
}