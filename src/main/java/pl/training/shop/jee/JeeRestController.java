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
    private final JndiTemplate jndiTemplate = new JndiTemplate();

    @GetMapping("exchange-rates")
    public double getExchangeRate(@RequestParam String value) throws NamingException {
        var exchangeRate = jndiTemplate.lookup("java:global/shop-1.0-SNAPSHOT/FakeExchangeRate", ExchangeRate.class);
        return exchangeRate.get(FastMoney.parse(value));
    }

    @PostMapping("messages")
    public ResponseEntity<Void> sendMessage(@RequestBody String text) {
        jmsSender.send(text);
        return ResponseEntity.accepted().build();
    }

}
