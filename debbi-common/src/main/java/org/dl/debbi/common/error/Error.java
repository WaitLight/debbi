package org.dl.debbi.common.error;

public interface Error {

    int code();

    String name();

    Status status();

    default DebbiException exception() {
        return DebbiException.of(this);
    }
}