package pl.training.shop.products;

import pl.training.shop.common.PagedResult;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    PagedResult<Product> findAll(int pageNumber, int pageSize);

    List<Product> findByNameContaining(String name);

}
