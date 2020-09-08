package pl.training.shop;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;
import pl.training.shop.orders.OrderService;
import pl.training.shop.payments.PaymentService;
import pl.training.shop.products.ProductService;

@EnableCaching
@Configuration
public class ShopConfiguration {

    @Bean
    public ShopService shopService(OrderService orderService, PaymentService paymentService, ProductService productService, TransactionTemplate transactionTemplate) {
        return new ShopService(orderService, paymentService, productService, transactionTemplate);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products");
    }

}
