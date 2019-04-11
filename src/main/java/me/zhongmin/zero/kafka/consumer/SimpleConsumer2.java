package me.zhongmin.zero.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class SimpleConsumer2 {

	public static void main(String[] args) {
		Properties pros = initPros();
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pros);
		IntStream.range(20, 30).forEach(i ->{
			ProducerRecord<String, String> pr = new ProducerRecord<>("test_c", "hello"+String.valueOf(i));
			Future<RecordMetadata> future= producer.send(pr);
			try {
				RecordMetadata data = future.get();
				log.info("消息发送完毕 key is {},offset {}",i,data.offset());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		p.put("client.id","demo-consumer-client");
		p.put("enable.auto.commit","false");
		p.put("group.id","test_group_1");
		return p;
	}
}
