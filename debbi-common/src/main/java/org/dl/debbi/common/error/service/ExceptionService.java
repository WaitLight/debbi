package org.dl.debbi.common.error.service;

import org.dl.debbi.common.error.domain.ExceptionInfo;

import java.util.List;

public interface ExceptionService {
    String getHash(Exception e);
    List<ExceptionInfo> explainError(String hash);
}
