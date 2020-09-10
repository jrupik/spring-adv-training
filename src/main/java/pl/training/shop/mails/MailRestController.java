package pl.training.shop.mails;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.shop.common.TextSource;

import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MailRestController {

    private final TextSource textSource;
    private final MailService mailService;

    @GetMapping("mails")
    public ResponseEntity<Void> sendMail(Locale locale) {
        String text = textSource.buildTemplate("hello", Map.of("user", "Jan"), locale.getLanguage());
        mailService.send(MailMessage.builder().recipient("l.andrzejewski@sages.com.pl").subject("Hello").text(text).build());
        return ResponseEntity.accepted().build();
    }

}
