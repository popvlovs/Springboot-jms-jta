package org.popvlovs.springwebapp.test.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "sample.topic")
    public void receiveQueue(String text) {
        System.out.println(text);
    }
}
