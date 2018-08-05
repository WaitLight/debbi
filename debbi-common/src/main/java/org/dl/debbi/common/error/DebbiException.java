package org.dl.debbi.common.error;

public class DebbiException extends RuntimeException {
    public DebbiException() {
    }

    public DebbiException(String message) {
        super(message);
    }

    public static DebbiException of(String message) {
        return new DebbiException(message);
    }
}
