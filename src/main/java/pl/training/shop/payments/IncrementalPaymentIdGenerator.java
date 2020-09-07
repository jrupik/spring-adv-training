package pl.training.shop.payments;

import lombok.Setter;
import pl.training.shop.common.profiler.ExecutionTime;

public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {

    private static final String ID_FORMAT = "%010d";

    @Setter
    private long index;

    @ExecutionTime
    @Override
    public String getNext() {
        return String.format(ID_FORMAT, ++index);
    }

}
