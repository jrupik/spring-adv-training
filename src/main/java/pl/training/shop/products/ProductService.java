package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
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
        //return productRepository.findByNameContaining(name);
        Product exampleProduct = Product.builder()
                .name(name)
                .build();
        /*return productRepository.findAll(Example.of(exampleProduct));*/
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("description")
                .withIncludeNullValues();
        return productRepository.findAll(Example.of(exampleProduct, exampleMatcher));
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        var productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }

}
