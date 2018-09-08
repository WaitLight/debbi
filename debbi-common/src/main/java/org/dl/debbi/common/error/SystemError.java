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
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public int getStatusCode() {
        return status.code();
    }

    @Override
    public String getStatusName() {
        return status.name().toLowerCase();
    }

}
