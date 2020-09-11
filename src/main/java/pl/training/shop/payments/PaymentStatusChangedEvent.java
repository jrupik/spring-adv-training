package pl.training.shop.payments;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PaymentStatusChangedEvent {

    @Getter
    private final Payment payment;

    public PaymentStatusChangedEvent(Payment payment) {
        this.payment = payment;
    }

}
