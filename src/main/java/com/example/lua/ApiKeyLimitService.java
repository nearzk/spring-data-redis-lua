package com.example.lua;

/**
 * This is {@link ApiKeyLimitService}.
 *
 * @author zhangkun
 * @since 0.0.1
 */
public interface ApiKeyLimitService {

    Integer limitPerDay(String key, Integer max);
}
