package org.dl.debbi.user.error;

import org.dl.debbi.common.error.DebbiException;

public enum AccountError {
    InvalidPrincipal;

    private int code;

    public DebbiException exception() {
        return DebbiException.of(this.toString());
    }
}
