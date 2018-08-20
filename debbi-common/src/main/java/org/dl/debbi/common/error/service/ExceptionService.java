package org.dl.debbi.common.error.service;

import org.dl.debbi.common.error.domain.ErrorLog;

import java.util.List;

public interface ExceptionService {
    String getHash(Exception e);
    ErrorLog explainError(String hash);
}
