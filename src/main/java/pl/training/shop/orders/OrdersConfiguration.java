package pl.training.shop.orders;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersConfiguration {

    @Bean
    public OrderRepository orderRepository(SessionFactory sessionFactory) {
        return new HibernateOrderRepository(sessionFactory);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);                                         
    }

}
