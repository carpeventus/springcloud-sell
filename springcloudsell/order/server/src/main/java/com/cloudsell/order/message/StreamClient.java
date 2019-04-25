package com.cloudsell.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Haiyu
 * @date 2019/4/14 15:00
 */
public interface StreamClient {
    String INPUT = "messageIn";
    String OUTPUT = "messageOut";

    /**
     * Input通道
     * @return
     */
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    /**
     * Output通道
     * @return
     */
    @Output(StreamClient.OUTPUT)
    MessageChannel output();
}
