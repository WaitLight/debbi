package org.dl.debbi.common.error;

public enum CommonError implements Error {
    REQUIRED_ERROR_HASH(1001, Status.REQUIRED_ARGUMENT);

    private int code;
    private Status status;

    CommonError(int code, Status status) {
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
