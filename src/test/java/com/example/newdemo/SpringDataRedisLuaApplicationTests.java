package com.example.newdemo;

import com.example.config.LuaRedisAutoConfiguration;
import com.example.lua.RedisApiKeyLimitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuaRedisAutoConfiguration.class)
public class SpringDataRedisLuaApplicationTests {

    @Autowired
    RedisApiKeyLimitService redisApiKeyLimitService;
    @Test
    public void contextLoads() {

        redisApiKeyLimitService.limitPerDay("api-key:123",100);
    }

}
