package pl.training.shop.chat;

import lombok.Data;

@Data
public class Message {

    private String sender;
    private String recipient;
    private String text;

}
