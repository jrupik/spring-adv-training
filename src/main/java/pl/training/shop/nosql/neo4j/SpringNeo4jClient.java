package pl.training.shop.nosql.neo4j;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringNeo4jClient {

    @Setter
    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void init() {
        Person person = new Person("Marek");
        System.out.println(personRepository.save(person));
        System.out.println(personRepository.findByName("Marek"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringNeo4jClient.class, args);
    }

}
