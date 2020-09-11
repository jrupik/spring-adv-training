package pl.training.shop.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.logging.Level;

@Controller
@Log
@RequiredArgsConstructor
public class ChatSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    @SendTo("/chat-topic/messages")
    public Message send(Message message) {
        log.log(Level.INFO, "New message:" + message);
        return message;
    }

}
