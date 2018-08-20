package org.dl.debbi.user.account.config;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public PasswordService passwordService(HashService hashService) {
        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);
        return passwordService;
    }

    @Bean
    public HashService hashService() {
        DefaultHashService defaultHashService = new DefaultHashService();
        // 私有盐，不会持久化
        defaultHashService.setPrivateSalt(new SimpleByteSource("cM#r&C~V"));
        // 在没有公盐时，是否生成
        defaultHashService.setGeneratePublicSalt(true);
        return defaultHashService;
    }
}
