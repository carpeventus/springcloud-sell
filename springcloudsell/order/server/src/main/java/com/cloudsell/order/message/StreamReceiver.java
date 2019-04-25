package com.cloudsell.order.message;

import com.cloudsell.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author Haiyu
 * @date 2019/4/14 15:01
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    /**
     * 监听OUTPUT通道，名为messageOut
     * @param message
     */
//    @StreamListener(StreamClient.OUTPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

    @StreamListener(StreamClient.OUTPUT)
    @SendTo(StreamClient.INPUT)
    public String  process(OrderDTO message) {
        log.info("StreamReceiver: {}", message);
        return "INPUT RECEIVED";
    }

    @StreamListener(StreamClient.INPUT)
    public void process2(String  message) {
        log.info("StreamReceiver: {}", message);
    }
}
