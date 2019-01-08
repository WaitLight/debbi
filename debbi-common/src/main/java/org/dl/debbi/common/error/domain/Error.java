package org.dl.debbi.common.error.domain;

public interface Error {
    int getCode();

    String getName();

    RuntimeException e();
}
