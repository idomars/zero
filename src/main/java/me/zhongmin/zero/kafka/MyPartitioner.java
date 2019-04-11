package me.zhongmin.zero.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

@Slf4j
public class MyPartitioner implements Partitioner {

    private final static String[] BIZ_TYPE = new String[]{"LOGIN","LOGOFF","ORDER"};

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {

        if(bytes == null || bytes.length == 0){
            log.info("bytes 不可为null");
        }

        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
