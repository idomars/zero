package me.zhongmin.zero.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
//    redisTemplate.opsForValue();//操作字符串
//redisTemplate.opsForHash();//操作hash
//redisTemplate.opsForList();//操作list
//redisTemplate.opsForSet();//操作set
//redisTemplate.opsForZSet();//操作有序set
    @Autowired private StringRedisTemplate template;

    @PostMapping("/string/set")
    public @ResponseBody String evn(String key,String value){
        template.opsForValue().set(key,value);

        String rv = template.opsForValue().get(key);
        return rv;
    }

    @PostMapping("/list/left/{key}")
    public @ResponseBody String evnlist(@PathVariable String key,String value){
        template.opsForList().leftPush(key,value);

        List<String> range = template.opsForList().range(key, 0, 100);

        return range.toString();
    }

    @PostMapping("/list/right/{key}")
    public @ResponseBody String addrightlist(@PathVariable String key,String value){
        template.opsForList().rightPush(key,value);

        List<String> range = template.opsForList().range(key, 0, 100);

        return range.toString();
    }

    @PostMapping("/list/{key}")
    public @ResponseBody String addrighlefttlist(@PathVariable String key,String value){
        template.opsForList().rightPopAndLeftPush(key,value);

        List<String> range = template.opsForList().range(key, 0, 100);

        return range.toString();
    }
    @PostMapping("/hash/{key}")
    public @ResponseBody String addhash(@PathVariable String key,String value){
        template.opsForHash().put("hash_key",key,value);
        Map<Object, Object> entries = template.opsForHash().entries("hash_key");
        return entries.toString();
    }
    @GetMapping("/String/{key}")
    public @ResponseBody String getString(@PathVariable String key,String value){
        template.boundGeoOps(key);
        Map<Object, Object> entries = template.opsForHash().entries("hash_key");
        return entries.toString();
    }
}
