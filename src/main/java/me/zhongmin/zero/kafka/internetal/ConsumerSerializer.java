package me.zhongmin.zero.kafka.internetal;

import lombok.extern.slf4j.Slf4j;
import me.zhongmin.zero.kafka.PropertieUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class ConsumerSerializer {

    public static void main(String[] args) {
        Properties pros = initPros();
        KafkaConsumer<String,User> consumer = new KafkaConsumer<String, User>(pros);
        consumer.subscribe(Collections.singletonList("test_1"));
        for(;;){
            ConsumerRecords<String, User> records = consumer.poll(100);
            records.forEach(record -> {
                record.timestamp();
                System.out.println("value="+record.value());
                System.out.println(record.toString());
            });

        }
    }

    private static Properties initPros(){
        Properties p = new Properties();
        p.put("bootstrap.servers", "192.168.24.128:9092,192.168.24.129:9092,192.168.24.130:9092");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "me.zhongmin.zero.kafka.internetal.UserDeserializer");
        p.put("client.id","demo-consumer-client");
        p.put("enable.auto.commit","false");
        p.put("group.id","group_1");
        p.put("auto.offset.reset","earliest");
        return p;
    }
}
