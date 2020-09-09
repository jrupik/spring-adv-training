package pl.training.shop.movies;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;

}
