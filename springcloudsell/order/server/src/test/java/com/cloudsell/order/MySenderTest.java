package com.cloudsell.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/4/13 16:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MySenderTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now "+new Date());
    }

    @Test
    public void sendOrder() {
        amqpTemplate.convertAndSend("computer","now " + new Date());
    }
}
