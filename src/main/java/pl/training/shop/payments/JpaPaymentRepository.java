package pl.training.shop.payments;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaPaymentRepository implements PaymentRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        entityManager.flush();
        entityManager.refresh(payment);
        return payment;
    }

}
