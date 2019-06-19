package com.example.config;

import com.example.lua.RedisApiKeyLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * This is {@link LuaRedisAutoConfiguration}.
 *
 * @author zhangkun
 * @since 2.0.0
 */
@Configuration
@Import(org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class)
public class LuaRedisAutoConfiguration {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public RedisApiKeyLimitService redisApiKeyLimitService() {
        return new RedisApiKeyLimitService(redisTemplate);
    }
}
