package pl.training.shop.payments;

import lombok.extern.java.Log;

@Log
public class PaymentConsoleLogger {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getMoney());
    }

}
