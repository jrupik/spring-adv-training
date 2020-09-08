package pl.training.shop.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.training.shop.common.PagedResult;
import pl.training.shop.payments.Payment;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, ProductRepositoryCustom {

    List<Product> findByNameContaining(String name);

    @Query("select new pl.training.shop.products.ProductSuperLite(p.name, p.description) from Product p where p.description = :desc")
    List<ProductSuperLite> findByDescription(@Param("desc") String description);

    /*@Query("select p.name as name, p.description as description from Product p where p.description = :desc")
    List<ProductLite> findByDescription(@Param("desc") String description);*/

}
