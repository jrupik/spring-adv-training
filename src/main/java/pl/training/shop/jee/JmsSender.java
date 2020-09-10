package pl.training.shop.jee;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@RequiredArgsConstructor
public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final Queue messagesQueue;

    public void send(String text) {
        jmsTemplate.send(messagesQueue, session -> session.createTextMessage(text));
    }

}
