package pl.training.shop.mails;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.jms.Queue;

@Log
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final Queue mailQueue;

    @Value("${sender}")
    @Setter
    private String sender;

    @SneakyThrows
    public void send(MailMessage mailMessage) {
        jmsTemplate.convertAndSend(mailQueue.getQueueName(), mailMessage);
    }

    @JmsListener(destination = "MailDS")
    public void onMessage(MailMessage mailMessage) {
        mailSender.send(createMimeMessagePreparator(mailMessage));
    }

    private MimeMessagePreparator createMimeMessagePreparator(MailMessage mailMessage) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(sender);
            messageHelper.setTo(mailMessage.getRecipient());
            messageHelper.setSubject(mailMessage.getSubject());
            messageHelper.setText(mailMessage.getText());
        };
    }

}
