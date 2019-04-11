package me.zhongmin.zero.kafka.inteceptor;

import lombok.extern.slf4j.Slf4j;
import me.zhongmin.zero.kafka.internetal.User;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG;

@Slf4j
public class ConsumerReceive {

    public static void main(String[] args) {
        Properties pros = initPros();
        KafkaConsumer<String,User> consumer = new KafkaConsumer<String, User>(pros);
        consumer.subscribe(Collections.singletonList("test_interceptor"));
        for(;;){
            ConsumerRecords<String, User> records = consumer.poll(100);
            records.forEach(record -> {
                log.info("partition = {} ,value = {},offset = ",record.partition(),record.value(),record.offset());
            });
        }
    }

    private static Properties initPros(){
        Properties p = new Properties();
        p.put("bootstrap.servers", "192.168.24.128:9092,192.168.24.129:9092,192.168.24.130:9092");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "me.zhongmin.zero.kafka.internetal.UserDeserializer");
        p.put("client.id","demo-consumer-client");
        p.put("auto.commit.interval.ms","10000");
        p.put("group.id","group_4");
        p.put("auto.offset.reset","earliest");
        p.put(INTERCEPTOR_CLASSES_CONFIG,"me.zhongmin.zero.kafka.inteceptor.MyConsumerInterceptor");
        return p;
    }
}
