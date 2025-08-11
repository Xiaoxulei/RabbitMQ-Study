package com.xuxiaolei;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/11 15:31
 **/
@SpringBootTest
public class RabbitMQTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String QUEUE_NAME  = "queue.order";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY,"Hello Rabbit!Springboot");
    }

}
