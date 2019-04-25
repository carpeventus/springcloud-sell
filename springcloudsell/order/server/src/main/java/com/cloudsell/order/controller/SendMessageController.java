package com.cloudsell.order.controller;

import com.cloudsell.order.dto.OrderDTO;
import com.cloudsell.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haiyu
 * @date 2019/4/14 15:04
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public void send() {
//        String message = "now "+new Date();
//        streamClient.output().send(MessageBuilder.withPayload(message).build());
//    }

    @GetMapping("/sendMessage")
    public void send() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("12345");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
