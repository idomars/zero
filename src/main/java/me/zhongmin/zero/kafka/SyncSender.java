package me.zhongmin.zero.kafka;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
@Slf4j
public class SyncSender {

	public static void main(String[] args) {
		Properties pros = initPros();
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pros);
		IntStream.range(0, 10).forEach(i ->{
			ProducerRecord<String, String> pr = new ProducerRecord<>("fire_and_forget_sender", "hellosync"+String.valueOf(i));
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
		return p;
	}
}
