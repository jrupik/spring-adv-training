package pl.training.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import pl.training.shop.common.PagedResult;
import pl.training.shop.orders.Order;
import pl.training.shop.orders.OrderService;
import pl.training.shop.payments.Payment;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentService;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductService;

import java.util.List;

@RequiredArgsConstructor
public class ShopService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final TransactionTemplate transactionTemplate;

    public Product addProduct(Product product) {
        return transactionTemplate.execute(txStatus -> {
            var savedProduct = productService.add(product);
            //txStatus.setRollbackOnly();
            return savedProduct;
        });
    }

    @Transactional
    public PagedResult<Product> getProducts(int pageNumber, int pageSize) {
        return productService.getAll(pageNumber, pageSize);
    }

    @Transactional
    public Order placeOrder(Order order) {
        return orderService.add(order);
    }

    @Transactional
    public Payment payForOrder(long orderId) {
        var order = orderService.getBy(orderId);
        var paymentRequest = PaymentRequest.builder()
                .money(order.getTotalPrice())
                .build();
        var payment = paymentService.process(paymentRequest);
        order.setPayment(payment);
        orderService.update(order);
        return payment;
    }

    @Transactional
    public List<Product> getProductsByName(String name) {
        return productService.getByName(name);
    }

}
