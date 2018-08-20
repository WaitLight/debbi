package org.dl.debbi.user;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.Utf8StringCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.url}")
    private String url;

    @Bean
    public RedisCommands<String, String> client() {
        RedisClient client = RedisClient.create(url);
        return client.connect(new Utf8StringCodec()).sync();
    }

}