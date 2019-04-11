package me.zhongmin.zero.kafka.consumer;


import lombok.extern.slf4j.Slf4j;
import me.zhongmin.zero.kafka.PropertieUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

@Slf4j
public class SyncConsumerCommit {


    public static void main(String[] args) {
        Properties pros = PropertieUtils.initConsumerPros();
        pros.put("enable.auto.commit","false");
        pros.put("group.id","syncgroup");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(pros);
        consumer.subscribe(Collections.singletonList("test_c"));
        for(;;){
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(record -> {
                record.timestamp();
                record.toString();
                System.out.println(record.toString());
            });
           // consumer.commitSync();
        }
    }
}
