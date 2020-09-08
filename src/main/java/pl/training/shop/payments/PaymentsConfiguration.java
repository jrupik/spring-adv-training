package pl.training.shop.payments;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new JpaPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository, ApplicationEventPublisher eventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger(MessageSource messageSource) {
        return new PaymentConsoleLogger(messageSource);
    }

    @Bean
    public PaymentFileLogger paymentFileLogger() {
        return new PaymentFileLogger(Paths.get("logs.txt"));
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }

}
