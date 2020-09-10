package pl.training.shop;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Setter
    @Autowired
    private UserService userServiceRemote;

    @PostConstruct
    public void init() {
        new Thread(() -> {
            try {
                Thread.sleep(6_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userServiceRemote.add(new User("Jan", "Kowalski"));
            System.out.println(userServiceRemote.getAll());
        }).start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
