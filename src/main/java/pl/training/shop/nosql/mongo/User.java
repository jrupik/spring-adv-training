package pl.training.shop.nosql.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Builder
@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;

}
