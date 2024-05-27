package service;

import java.util.List;
import java.util.stream.Collectors;

import domain.Movie;
import domain.Review;
import dto.MovieDTO;
import dto.ReviewDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import repository.MovieRepository;
import util.JpaUtil;

public class MovieService {
	private static MovieService instance;
    private static final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
    private MovieRepository movieRepository;

    private MovieService() {
        movieRepository = MovieRepository.getInstance();
    }

    public static synchronized MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public List<MovieDTO> searchMoviesByTitle(String movieTitle) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            List<Movie> movies = movieRepository.searchMoviesByTitle(movieTitle, em);
            List<MovieDTO> movieDTOs = movies.stream()
                    .map(MovieDTO::fromEntity)
                    .collect(Collectors.toList());

            tx.commit();
            return movieDTOs;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getMoviesByRatingDesc() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            List<Movie> movies = movieRepository.findMoviesByRatingDesc(em);
            List<MovieDTO> movieDTOs = movies.stream()
                    .map(MovieDTO::fromEntity)
                    .collect(Collectors.toList());

            tx.commit();
            return movieDTOs;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public double getAverageRating(MovieDTO movieDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            double averageRating = movieRepository.getAverageRating(movieDTO, em);

            tx.commit();
            return averageRating;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public MovieDTO getMovie(int movieId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            Movie movie = movieRepository.findById(movieId, em);
            MovieDTO movieDTO = MovieDTO.fromEntity(movie);

            tx.commit();
            return movieDTO;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}