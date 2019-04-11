package me.zhongmin.zero.kafka.internetal;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class ProducerSerializer {

	public static void main(String[] args) {
		Properties pros = initPros();
		KafkaProducer<String, User> producer = new KafkaProducer<String, User>(pros);
		IntStream.range(0, 10).forEach(i ->{
			ProducerRecord<String, User> pr = new ProducerRecord<>("test_1",new User(i,"name-"+i,"address-"+i));
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
		p.put("value.serializer", "me.zhongmin.zero.kafka.internetal.UserSerializer");
		return p;
	}
}
