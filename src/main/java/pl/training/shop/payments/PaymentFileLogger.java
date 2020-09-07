package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Aspect
@RequiredArgsConstructor
public class PaymentFileLogger implements Ordered {

    private final Path path;

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    @SneakyThrows
    public void log(Payment payment) {
        Files.write(path, payment.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
