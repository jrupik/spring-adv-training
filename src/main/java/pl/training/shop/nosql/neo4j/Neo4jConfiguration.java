package pl.training.shop.nosql.neo4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

//@EnableNeo4jRepositories(basePackages = "pl.training.mongodb")
@Configuration
public class Neo4jConfiguration {

    /*@Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new org.neo4j.ogm.config.Configuration.Builder().uri("bolt://localhost:7687")
                .build();
    }*/

}
