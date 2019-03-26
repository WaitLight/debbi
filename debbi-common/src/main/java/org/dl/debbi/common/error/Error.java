package org.dl.debbi.common.error;

public interface Error {

  int getCode();

  String getName();

  RuntimeException e();
}
