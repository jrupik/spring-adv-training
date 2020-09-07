package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pl.training.shop.common.PagedResult;

import java.util.List;

@RequiredArgsConstructor
@Log
public class ProductService {

    private final ProductRepository productRepository;

    @CacheEvict("products")
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> getByName(String name) {
        log.info("Reading products from database...");
        return productRepository.findByNameContaining(name);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        return productRepository.findAll(pageNumber, pageSize);
    }

}
