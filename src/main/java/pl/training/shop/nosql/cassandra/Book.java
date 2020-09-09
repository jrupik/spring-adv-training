package pl.training.shop.nosql.cassandra;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Builder
@Data
public class Book {

    @PrimaryKey("uuid")
    private UUID id;
    private String title;
    private String author;

}
