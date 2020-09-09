package pl.training.shop.nosql.mongo;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

//@SpringBootApplication
public class SpringBootMongoClient {

    @Setter
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = User.builder()
                .firstName("Jan")
                .lastName("Nowak")
                .dateOfBirth(LocalDateTime.now())
                .build();

        System.out.println(userRepository.save(user));
        System.out.println(userRepository.findByLastName("Nowak"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoClient.class, args);
    }

}
