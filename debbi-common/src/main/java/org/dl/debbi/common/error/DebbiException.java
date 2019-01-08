package org.dl.debbi.common.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class DebbiException extends RuntimeException {

    private final Error error;

    private DebbiException(Error error) {
        super(error.getName() + " " + error.getCode());
        this.error = error;
    }

    public static DebbiException of(Error error) {
        return new DebbiException(error);
    }
}