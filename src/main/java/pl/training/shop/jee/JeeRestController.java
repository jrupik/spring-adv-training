package pl.training.shop.jee;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.http.ResponseEntity;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;

@RestController
@RequiredArgsConstructor
public class JeeRestController {

    private final JmsSender jmsSender;
    private final ExchangeRate exchangeRate;

    @GetMapping("exchange-rates")
    public double getExchangeRate(@RequestParam String value) throws NamingException {
        return exchangeRate.get(FastMoney.parse(value));
    }

    @PostMapping("messages")
    public ResponseEntity<Void> sendMessage(@RequestBody String text) {
        jmsSender.send(text);
        return ResponseEntity.accepted().build();
    }

}
