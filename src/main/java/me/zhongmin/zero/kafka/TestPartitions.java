package me.zhongmin.zero.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class TestPartitions {

    public static void main(String[] args) throws Exception {
        Properties pros = initPros();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pros);
        IntStream.range(0, 10).forEach(i ->{
            ProducerRecord<String, String> pr = new ProducerRecord<>("test_p", "key","hello"+String.valueOf(i));
            Future<RecordMetadata> future = producer.send(pr);
            try {
                RecordMetadata recordMetadata = future.get();
                log.info("消息发送完毕 ,partitions is {}",recordMetadata.partition());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
        producer.flush();
        producer.close();

    }

    private static Properties initPros(){
        Properties p = new Properties();
        p.put("bootstrap.servers", "192.168.24.128:9092,192.168.24.129:9092,192.168.24.130:9092");
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return p;
    }
}
