package pl.training.shop.nosql.neo4j;

import org.neo4j.driver.*;

import java.util.Map;

public class SimpleNeo4jClient {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123"));
        try (Session session = driver.session()) {
            String name = session.writeTransaction(transaction -> {
                Result result = transaction.run("create (u:User) set u.name = 'Jan' return u");
                return result.single().get(0).get("name").asString();
            });
            System.out.println(name);
        }
    }

}
