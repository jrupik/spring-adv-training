package pl.training.shop;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Client {

    @Setter
    @Autowired
    private UserService userServiceRemote;

    @PostConstruct
    public void init() {
        userServiceRemote.add(new User("Jan", "Kowalski"));
        System.out.println(userServiceRemote.getAll());
    }

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

}
