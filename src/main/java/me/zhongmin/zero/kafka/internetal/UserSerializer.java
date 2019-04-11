package me.zhongmin.zero.kafka.internetal;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class UserSerializer implements Serializer<User> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, User user) {

        if(null == user){
            return null;
        }
        int id = user.getId();
        String name = user.getName();
        String address = user.getAddress();
        byte[] namebtys = new byte[0];
        if(name != null) {
            namebtys = name.getBytes();
        }
        byte[] addrbtys = new byte[0];
        if(null != address){
            addrbtys = address.getBytes();
        }
        ByteBuffer buffer = ByteBuffer.allocate(4+4+name.getBytes().length+4+address.getBytes().length);
        buffer.putInt(id);
        buffer.putInt(namebtys.length);
        buffer.put(namebtys);
        buffer.putInt(addrbtys.length);
        buffer.put(addrbtys);
        return buffer.array();
    }

    @Override
    public void close() {

    }
}
