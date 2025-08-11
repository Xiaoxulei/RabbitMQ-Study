package com.xuxiaolei.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/11 16:02
 **/
@Configuration
@Slf4j
public class RabbitConfig implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //消息发送到交换机成功或者失败时调用这个方法
        log.info("correlationData:" + correlationData);
        log.info("ack:" + ack);
        log.info("cause:" + cause);
    }

    @Override
    public void returnedMessage(@NonNull ReturnedMessage returnedMessage) {
        //发送到队列失败调用这个方法
        log.info("消息主体: " + new String(returnedMessage.getMessage().getBody()));
        log.info("应答码: " + returnedMessage.getReplyCode());
        log.info("描述：" + returnedMessage.getReplyText());
        log.info("消息使用的交换器 exchange : " + returnedMessage.getExchange());
        log.info("消息使用的路由键 routing : " + returnedMessage.getRoutingKey());
    }
}
