package org.dl.debbi.common.error.service;

import org.dl.debbi.common.error.domain.DebbiError;

public interface ExceptionService {
    String getErrorHash(Exception e);
    DebbiError explainError(String errorHash);
}
