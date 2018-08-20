package org.dl.debbi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class EncodeConfig {

    @Bean("encoder")
    public Base64.Encoder getEncoder() {
        return Base64.getEncoder();
    }

    @Bean("decoder")
    public Base64.Decoder getDecoder() {
        return Base64.getDecoder();
    }
}
