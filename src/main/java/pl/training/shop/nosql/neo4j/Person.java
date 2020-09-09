package pl.training.shop.nosql.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String name;

}
