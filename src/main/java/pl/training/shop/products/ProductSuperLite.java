package pl.training.shop.products;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ProductSuperLite {

    @NonNull
    private String name;
    @NonNull
    private String description;

}
