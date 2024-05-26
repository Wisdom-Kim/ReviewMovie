package service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Movie;
import dto.MovieDTO;
import javax.persistence.TypedQuery;
import repository.MovieRepository;

public class MovieService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_config");
    private EntityManager em = emf.createEntityManager();
    private final MovieRepository movieRepository = MovieRepository.getInstance();


    public List<MovieDTO> searchMoviesByTitle(String movieTitle) {
        List<Movie> movies = movieRepository.searchMoviesByTitle(movieTitle);
        return movies.stream()
                .map(MovieDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> getMoviesByRatingDesc() {
        List<Movie> movies = movieRepository.findMoviesByRatingDesc();
        return movies.stream()
                .map(MovieDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public double getAverageRating(MovieDTO movieDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT AVG(r.ratingScore) FROM Rating r WHERE r.review.movie.movieId = :movieId", Double.class
            );
            query.setParameter("movieId", movieDTO.getMovieId());
            Double averageRating = query.getSingleResult();
            return (averageRating != null) ? averageRating : 0.0; //해당하는 영화가 없다면 일단 0으로 반환
        } finally {
            em.close();
        }
    }


    public MovieDTO getMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId);
        return MovieDTO.fromEntity(movie);
    }

    public void close() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}