package pl.training.shop.movies;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Movie {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String genre;
    @NonNull
    private double rating;
    @NonNull
    private int releaseYear;
    private double duration;

}
