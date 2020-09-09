package pl.training.shop.nosql.cassandra;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
public class SpringCassandraClient {

    @Setter
    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        UUID id = UUID.randomUUID();
        Book book = Book.builder()
                .title("Cassandra")
                .author("Unknown")
                .id(id)
                .build();

        bookRepository.save(book);
        System.out.println(bookRepository.findById(id));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCassandraClient.class, args);
    }

}
