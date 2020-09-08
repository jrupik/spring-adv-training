package pl.training.shop.products;

import org.springframework.beans.factory.annotation.Value;

public interface ProductLite {

    String getName();
    String getDescription();
    @Value("#{target.name + ': ' + target.description}")
    String getFullInfo();

}
