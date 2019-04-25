package com.cloudsell.order.message;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Haiyu
 * @date 2019/4/13 16:02
 */
@Slf4j
@Component
public class MqReceiver {
//    @RabbitListener(queues = "myQueue")
//    自动创建队列，@RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    自动创建队列，Exchange和队列绑定
    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"), exchange = @Exchange("myExchange")))
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }

    /**
     * 数码供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            value = @Queue("computerOrder"),
            key = "computer"
    ))
    public void processComputer(String message) {
        log.info("MqReceiver: {}", message);
    }

    /**
     * 水果供应商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            value = @Queue("fruitOrder"),
            key = "fruit"))
    public void processFruit(String message) {
        log.info("MqReceiver: {}", message);
    }
}
