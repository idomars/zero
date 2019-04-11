package me.zhongmin.zero.kafka.reblance;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class ReblanceConsumer {


    public static void main(String[] args) {
        Properties pros = initPros();
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(pros);
        consumer.subscribe(Collections.singletonList("test2"), new MyConsumerBlanceListener(consumer));
        for(;;){
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(record -> {
                record.timestamp();
                record.toString();
                System.out.println(record.toString());
            });

        }
    }

    private static Properties initPros(){
        Properties p = new Properties();
        p.put("bootstrap.servers", "192.168.24.128:9092,192.168.24.129:9092,192.168.24.130:9092");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("client.id","c1");
        p.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        p.put("group.id","group_6");
//        p.put("auto.offset.reset","earliest");
        return p;
    }

    private static class MyConsumerBlanceListener implements ConsumerRebalanceListener {

        private final KafkaConsumer<String,String> kafkaConsumer;

        private MyConsumerBlanceListener(KafkaConsumer<String, String> kafkaConsumer) {
            this.kafkaConsumer = kafkaConsumer;
        }
        //当前的consumer产生异常是会监听到保存当前consumer读取partition到的offset
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            for(TopicPartition p : collection){
                long nextoffset = kafkaConsumer.position(p);//获取下一个位置
                //TODO save nextoffset to data store
                log.info("partitions = {},nextiffset = {}",p.partition(),nextoffset);
            }
        }
        //当partions被其他的consumer接管是可以设置读取的offset
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        }
    }
}
