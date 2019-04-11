package me.zhongmin.zero.kafka.permter;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;


import me.zhongmin.zero.kafka.PropertieUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


@Slf4j
public class AckProduceTest {
	public static void main(String[] args) {
		
		Properties pros = PropertieUtils.initPros();
		/**
		 *  acks = 0 时, 100000条数据  时间 =39160
		 *  acks = 1时 , 100000条数据 时间 =396387
		 *  acks = 2时 , 10000条数据 时间 =  77981   
		 *                                gzip  89301  snappy  89471
		 */
		pros.put("acks", "all");
		pros.put("compression.type", "snappy");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pros);
		Long start = System.currentTimeMillis();
		IntStream.range(0, 10000).forEach(i ->{
			ProducerRecord<String, String> pr = new ProducerRecord<>("acktest", "acktest"+String.valueOf(i));
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
		Long end = System.currentTimeMillis();
		log.info("总花费时间 {}",(end -start));
	}
}
