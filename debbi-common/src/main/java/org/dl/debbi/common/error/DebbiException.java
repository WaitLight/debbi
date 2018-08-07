package org.dl.debbi.common.error;

public class DebbiException extends RuntimeException {

    private int code;
    private String message;

    public DebbiException() {
    }

    public DebbiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DebbiException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static DebbiException of(ErrorType errorType) {
        return new DebbiException(errorType.toString(), errorType.getCode());
    }
}