package org.dl.debbi.common.id.service.impl;

import java.util.concurrent.ThreadLocalRandom;

public class SquareDetection {

  private final long max;
  private final long min;

  public SquareDetection(long min, long max) {
    this.min = min;
    this.max = max;
  }

  public long random(int attempts, Long last) {
    if (last == null) {
      return ThreadLocalRandom.current().nextLong(min, max);
    }

    long step = (long) Math.pow(attempts, 2L);
    long next = attempts % 2L == 0L ? last - step : last + step;
    next = handleOverRange(next);
    return next;
  }

  private long handleOverRange(long next) {
    while (min > next) {
      next = next == 0 ? max - min : max - min;
    }
    while (max < next) {
      next = min + (next % max);
    }
    return next;
  }


  public static class SquareDetectionBuilder {

    private Long min;
    private Long max;

    private SquareDetectionBuilder() {
    }

    public static SquareDetectionBuilder newBuilder() {
      return new SquareDetectionBuilder();
    }

    public SquareDetectionBuilder min(long min) {
      if (min < 0) {
        throw new RuntimeException();
      }
      this.min = min;
      return this;
    }

    public SquareDetectionBuilder max(long max) {
      if (this.min == null) {
        throw new RuntimeException();
      }
      long range = range(min, max);

      boolean rangeNotPrime = !isPrimeNumber(range);
      if (rangeNotPrime) {
        max = nearestPrimeMax(min, max);
      }
      this.max = max;
      return this;
    }

    public SquareDetection build() {
      return new SquareDetection(min, max);
    }

    private long range(long min, long max) {
      return max - min + 1L;
    }

    private long nearestPrimeMax(long min, long max) {
      long range = range(min, max);
      if (isPrimeNumber(range)) {
        return max;
      }
      for (long step = 1L; step < range; step++) {
        max -= step;
        if (isPrimeNumber(range(min, max))) {
          return max;
        }
      }
      throw new RuntimeException();
    }

    private boolean isPrimeNumber(long num) {
      if (num <= 1L) {
        return false;
      }
      for (long i = 2L; i <= Math.sqrt(num); i++) {
        if (num % i == 0L) {
          return false;
        }
      }
      return true;
    }
  }
}
