package org.dl.debbi.common.id.service;

public interface RetryIdGenerator {

  long next(long attempts);

  long[] nextBatch();
}
