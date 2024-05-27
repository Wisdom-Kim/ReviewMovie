package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name="movie_director",nullable = false)
    private String movieDirector;

    @Column(name = "movie_poster", nullable = false)
    private String moviePoster;

    @Column(name = "movie_type", nullable = false)
    private String movieType;

    @Column(name = "movie_release_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date movieReleaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();


}
