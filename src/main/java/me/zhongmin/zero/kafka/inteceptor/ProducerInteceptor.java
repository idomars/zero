package me.zhongmin.zero.kafka.inteceptor;

import me.zhongmin.zero.kafka.internetal.User;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInteceptor implements ProducerInterceptor<String,User> {
    @Override
    public ProducerRecord<String, User> onSend(ProducerRecord<String, User> pr) {

        User u = pr.value();
        User u1 = new User(u.getId(),u.getName().toUpperCase(),u.getAddress().toUpperCase());

        return new ProducerRecord<String, User>(pr.topic(),pr.partition(),pr.timestamp(),pr.key(),u1);
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
