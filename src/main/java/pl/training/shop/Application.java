package pl.training.shop;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.movies.Movie;
import pl.training.shop.movies.MovieService;
import pl.training.shop.movies.SearchCriteria;
import pl.training.shop.movies.SearchOperation;
import pl.training.shop.orders.Order;
import pl.training.shop.payments.LocalMoney;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductRepository;
import pl.training.shop.products.ProductType;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@Log
public class Application {

    private static final String BASE_PACKAGE = "pl.training.shop";
    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring framework")
            .type(ProductType.VIDEO)
            .price(LocalMoney.of(799))
            .build();
    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring guide")
            .description("Praktyczne ćwiczenia do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(LocalMoney.of(200))
            .build();

    @Setter
    @Autowired
    private ShopService shopService;
    @Setter
    @Autowired
    private ProductRepository productRepository;
    @Setter
    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void init() {
        shopService.addProduct(VIDEO_PRODUCT);
        shopService.addProduct(BOOK_PRODUCT);
        log.info(shopService.getProducts(0, 100).toString());

        var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
        shopService.placeOrder(order);
        var payment = shopService.payForOrder(order.getId());
        log.info(payment.toString());

       // System.out.println(productRepository.findByDescription("Praktyczny kurs Spring framework").get(0).getDescription());

        movieService.add(List.of(
                new Movie("Terminator", "Action", 4.5, 2000),
                new Movie("Alien", "Action", 4.5, 1999)
        ));

        var searchCriteria = List.of(
                new SearchCriteria("genre", "Action", SearchOperation.EQUAL),
                new SearchCriteria("releaseYear", 2000, SearchOperation.EQUAL)
        );
        var results = movieService.findByCriteria(searchCriteria);
        System.out.println(results);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
