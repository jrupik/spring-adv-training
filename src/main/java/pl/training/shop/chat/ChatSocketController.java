package pl.training.shop.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Controller
@Log
@RequiredArgsConstructor
public class ChatSocketController  implements ApplicationListener<SessionConnectedEvent> {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> sockets = new HashMap<>();

    @MessageMapping("/chat")
    public void sendSpecific(@Payload Message message, Principal user, @Header("simpSessionId") String sessionId) {
        sockets.put(message.getSender(), sessionId);
        messagingTemplate.convertAndSend("/queue/specific-user-user" + sockets.get(message.getRecipient()), message);
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        log.info(sessionConnectedEvent.toString());
    }

    /*@MessageMapping("/chat")
    @SendTo("/chat-topic/messages")
    public Message send(Message message) {
        log.log(Level.INFO, "New message:" + message);
        return message;
    }*/

}
