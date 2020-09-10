package pl.training.shop;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.shop.producer.KafkaProducer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Setter
    @Autowired
    private KafkaProducer kafkaProducer;
    @Setter
    @Value("${training.kafka.mainTopic}")
    private String mainTopic;

    @PostConstruct
    public void init() {
        kafkaProducer.send("Hello Kafka", mainTopic);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
