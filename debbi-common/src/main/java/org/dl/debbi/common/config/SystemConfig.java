package org.dl.debbi.common.config;

/**
 * 系统通用配置，各种用户测试的
 */
public class SystemConfig {
  // 是否开启预置用户
  public static boolean ENABLE_PRESET_USER = true;

  // 是否启用错误hash信息
  public static boolean ENABLE_ERROR_HASH = true;

  // 启用测试验证码
  public static boolean ENABLE_TEST_CODE = true;
  public static String TEST_CODE = "123456";
}
