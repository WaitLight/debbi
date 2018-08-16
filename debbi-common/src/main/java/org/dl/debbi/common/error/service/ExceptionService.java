package org.dl.debbi.common.error.service;

public interface ExceptionService {
    String getHash(Exception e);
    Object resolveHash(String hash);
}
