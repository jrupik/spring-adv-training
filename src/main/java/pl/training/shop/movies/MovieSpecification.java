package pl.training.shop.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MovieSpecification implements Specification<Movie> {

    private final List<SearchCriteria> searchCriteria;

    public  void add(SearchCriteria searchCriteria) {
        this.searchCriteria.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria: searchCriteria) {
            switch (criteria.getOperation()) {
                case GREATER_THEN:
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case LESS_THEN:
                    predicates.add(builder.lessThan(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case GREATER_OR_EQUAL_THEN:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case LESS_OR_EQUAL_THEN:
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(root.get(criteria.getKey()),criteria.getValue().toString()));
                    break;
                case MATCH:
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%")
                    );
                    break;
                case MATCH_START:
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase())
                    );
                    break;
                case MATCH_END:
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%")
                    );
                    break;
                case IN:
                    predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue().toString()));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(builder.in(root.get(criteria.getKey())).value(criteria.getValue().toString())));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
