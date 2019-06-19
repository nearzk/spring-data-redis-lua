package com.example.lua;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * This is {@link RedisApiKeyLimitService}.
 *
 * @author zhangkun
 * @since 2.0.0
 */
@AllArgsConstructor
public class RedisApiKeyLimitService implements ApiKeyLimitService {

    private static final Integer MAX_TIMES = 100;
    private final StringRedisTemplate redisTemplate;

    @Override
    public Integer limitPerDay(String key) {
        return limitPerDay(key, null);
    }

    @Override
    public Integer limitPerDay(String key, Integer max) {

        DefaultRedisScript script = new DefaultRedisScript();
        script.setResultType(Integer.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("/api-key-limit.lua")));

        LocalDateTime current = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(current.getYear(), current.getMonth(), current.getDayOfMonth(), 23, 59, 59);
        Duration duration = Duration.between(current, end);
        long seconds = duration.getSeconds();
        String live = Long.valueOf(seconds).toString();

        max = max == null ? MAX_TIMES : max;
        Integer timeToLive = (Integer) redisTemplate.execute(script, Collections.singletonList(key), live, max);
        return timeToLive;
    }
}
