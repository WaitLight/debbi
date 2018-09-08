package org.dl.debbi.common.error;

public final class DebbiException extends RuntimeException {

    public Error error;
    public Object data;

    private DebbiException(Error error) {
        super(error.getCode() + " " + error.getName());
        this.error = error;
    }

    private DebbiException(Error error, Object data) {
        super(error.getCode() + " " + error.getName());
        this.error = error;
        this.data = data;
    }

    public static DebbiException of(Error errorType) {
        return new DebbiException(errorType);
    }

    public static DebbiException of(Error errorType, String data) {
        return new DebbiException(errorType, data);
    }

    public String getErr() {
        return error.getName();
    }

    public int getCode() {
        return error.getCode();
    }

    public Object getData() {
        return this.data;
    }
}