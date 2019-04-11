package me.zhongmin.zero.kafka.inteceptor;

import me.zhongmin.zero.kafka.internetal.User;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyConsumerInterceptor implements ConsumerInterceptor<String,User> {
    @Override
    public ConsumerRecords<String, User> onConsume(ConsumerRecords<String, User> consumerRecords) {
        Map<TopicPartition,List<ConsumerRecord<String,User>>> rmap = new HashMap<>();
        Set<TopicPartition> sets = consumerRecords.partitions();
        for(TopicPartition partition : sets){
            List<ConsumerRecord<String,User>> list = consumerRecords.records(partition);
            List<ConsumerRecord<String, User>> recordList = consumerRecords.records(partition).stream().filter(record -> {
                User u = record.value();
                return u.getId() == 5;
            }).collect(Collectors.toList());
            rmap.put(partition,recordList);
        }

        return new ConsumerRecords<>(rmap);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        System.out.println("commit===="+ map);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
