package org.dl.debbi.common.error;

public class DebbiException extends RuntimeException {

    private ErrorType type;
    private String message;

    public DebbiException() {
    }

    public DebbiException(ErrorType type) {
        this.type = type;
    }

    public DebbiException(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }

    public static DebbiException of(ErrorType errorType) {
        return new DebbiException(errorType);
    }

    public static DebbiException of(ErrorType errorType, String message) {
        return new DebbiException(errorType, message);
    }
}