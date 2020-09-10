package pl.training.shop.mails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessage implements Serializable {

    private String recipient;
    private String subject;
    private String text;

}
