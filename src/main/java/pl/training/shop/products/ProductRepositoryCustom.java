package pl.training.shop.products;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByType(ProductType productType);

}
