package org.dl.debbi.common.error;

public interface Error {

    int getCode();

    String getName();

    default DebbiException exception() {
        return DebbiException.of(this);
    }

    default DebbiException exception(String message) {
        return DebbiException.of(this, message);
    }
}