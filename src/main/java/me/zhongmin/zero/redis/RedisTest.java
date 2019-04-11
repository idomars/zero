package me.zhongmin.zero.redis;

import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisTest {

   // @Test
    public void testRedisConnection(){
        StringRedisTemplate template = new StringRedisTemplate();
        template.opsForValue().set("spring-boot-redis","yes");

    }
}
