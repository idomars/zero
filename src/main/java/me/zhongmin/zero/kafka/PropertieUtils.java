package me.zhongmin.zero.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class PropertieUtils {
	
	public static Properties initPros() {
		Properties properties = new Properties();
		InputStream is = PropertieUtils.class.getClassLoader().getResourceAsStream("kafka.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> keyset = properties.keySet();
		for(Object key : keyset){
			
			log.info(" key = {} ,value = {}",key.toString(),properties.get(key));
		}
		
		return properties;
	}
	
	@Test
	public  void initPros1() throws IOException{
		Properties properties = new Properties();
		InputStream is = PropertieUtils.class.getClassLoader().getResourceAsStream("kafka.properties");
		properties.load(is);
		Set<Object> keyset = properties.keySet();
		for(Object key : keyset){
			
			log.info(" key = {} ,value = {}",key.toString(),properties.get(key));
		}
		
		return ;
	}


	public static  Properties initConsumerPros() {
		Properties properties = new Properties();
		InputStream is = PropertieUtils.class.getClassLoader().getResourceAsStream("kafka-consumer.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> keyset = properties.keySet();
		for(Object key : keyset){

			log.info(" key = {} ,value = {}",key.toString(),properties.get(key));
		}

		return properties;
	}
}
