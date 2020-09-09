package pl.training.shop.movies;

import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findByCriteria(List<SearchCriteria> searchCriteria) {
        return movieRepository.findAll(new MovieSpecification(searchCriteria));
    }

    public void add(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public List<Movie> getByTitle(String title) {
        return movieRepository.findAll((root, criteriaQuery, builder) -> builder
                .like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
    }

}
