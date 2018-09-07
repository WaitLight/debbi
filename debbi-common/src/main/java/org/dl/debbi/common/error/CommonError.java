package org.dl.debbi.common.error;

public enum CommonError implements Error {
    REQUIRED_ERROR_HASH(1001, Status.REQUIRED_ARGUMENT);

    private int code;
    private Status basicError;

    CommonError(int code, Status basicError) {
        this.code = code;
        this.basicError = basicError;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public Status status() {
        return basicError;
    }
}
