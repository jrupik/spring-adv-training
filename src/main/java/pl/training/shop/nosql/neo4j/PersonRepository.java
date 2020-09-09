package pl.training.shop.nosql.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository  extends Neo4jRepository<Person, Long> {

    List<Person> findByName(String name);

}