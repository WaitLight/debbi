package org.dl.debbi.common.error;

public enum SystemError implements Error {
    UNAUTHORIZED(1001, Status.UNAUTHORIZED),
    ;


    private int code;
    private Status status;

    SystemError(int code, Status status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public Status status() {
        return status;
    }
}
