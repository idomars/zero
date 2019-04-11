package me.zhongmin.zero.rabbitmq;

import me.zhongmin.zero.ZeroApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestRabbitMQ {

    @Autowired
    private AmqpTemplate rabbitTemplate;

//    @Test
    public void sender() {
        rabbitTemplate.convertAndSend("hello", "hello,rabbitMQ,it.s me");
    }

    /**
     * 消息接受
     *
     * @param message
     */
    @RabbitListener(queues = "hello")  //监听器监听指定的QueueName
    public void receive(String message) {
        System.out.println("接收消息:" + message);
    }
}
