package me.zhongmin.zero.redis;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheManagerConsumer {

//    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer(){
//        return new CacheManagerCustomizer<RedisCacheManager>() {
//            @Override
//            public void customize(RedisCacheManager cacheManager) {
//                Map<String,Long> expires = new HashMap<>();
//                expires.put("user",10L);
//
//            }
//        }
//    }
//    @Bean
//    public RedisCacheManager cacheManager(RedisTemplate<String,Object> template) {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(template);
//        // 开启使用缓存名称最为key前缀
//        redisCacheManager.setUsePrefix(true);
//        //这里可以设置一个默认的过期时间 单位是秒
//        redisCacheManager.setDefaultExpiration(redisDefaultExpiration);
//
//        // 设置缓存的过期时间
//        Map<String, Long> expires = new HashMap<>();
//        expires.put("people", 1000);
//        redisCacheManager.setExpires(expires);
//
//        return redisCacheManager;
//    }


}
