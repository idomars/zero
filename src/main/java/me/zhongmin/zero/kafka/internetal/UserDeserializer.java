package me.zhongmin.zero.kafka.internetal;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

@Slf4j
public class UserDeserializer implements Deserializer<User> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public User deserialize(String s, byte[] bytes) {

        if(bytes == null){
            return null;
        }

        if(bytes.length < 12){
            log.info("长度不可小于12");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int id = buffer.getInt();
        log.info("id = {}",id);

        int namelen = buffer.getInt();
        byte[] namebyte = new byte[namelen];
        buffer.get(namebyte);
        String name = new String(namebyte);
        log.info("name = {}",name);

        int addrlen = buffer.getInt();
        byte[] addrbyte = new byte[addrlen];
        buffer.get(addrbyte);
        String addr = new String(addrbyte);
        log.info("addr = {}",addr);
        return new User(id,name,addr);
    }

    @Override
    public void close() {

    }
}

