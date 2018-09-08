package org.dl.debbi.common.error;

public interface Error {

    int getCode();

    String getName();

    int getStatusCode();

    String getStatusName();

    default DebbiException exception() {
        return DebbiException.of(this);
    }
}