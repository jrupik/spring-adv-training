package pl.training.shop.products;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.shop.common.PagedResult;
import pl.training.shop.payments.Payment;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByNameContaining(String name);



}
