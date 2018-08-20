package org.dl.debbi.user.code.impl;

import io.lettuce.core.api.sync.RedisCommands;
import org.dl.debbi.user.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private RedisCommands<String, String> redis;

    @Override
    public String get(long userId) {
        redis.setex("pa:" + userId, 30, "1234");
        return "1234";
    }

    @Override
    public boolean verify(long userId, String code) {
        String s = redis.get("pa:" + userId);
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        if (s.equals(code)) {
            redis.del("pa:" + userId);
            return true;
        }
        return false;
    }
}
