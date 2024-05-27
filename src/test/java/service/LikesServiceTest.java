package service;

import domain.Movie;
import domain.User;
import dto.LikesDTO;
import dto.RatingDTO;
import dto.ReviewDTO;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LikesServiceTest {

    private final LikesService likesService = LikesService.getInstance();
    private final UserService userService = new UserService();
    private final ReviewService reviewService = ReviewService.getInstance();
    private final MovieService movieService = new MovieService();
    
    @Test
    void insertLikes() {
        User user = userService.getUser("cocoa389", "1234").toEntity();
        Movie movie = movieService.getMovie(102).toEntity();
        RatingDTO ratingDTO = RatingDTO.builder().ratingScore(5).build();
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewContent("Great movie!")
                .reviewDate(new Date())
                .userId(user.getUserId())
                .movieId(movie.getMovieId())
                .ratingScore(ratingDTO.getRatingScore())
                .build();

        reviewService.insertReview(reviewDTO, ratingDTO);

        LikesDTO likesDTO = LikesDTO.builder()
                .userId(user.getUserId())
                .reviewId(reviewDTO.getReviewId())
                .build();

        // When
        likesService.insertLikes(likesDTO);

        // Then
        LikesDTO retrievedLikes = likesService.getLikes(likesDTO.getLikesId());
        assertNotNull(retrievedLikes);
        assertEquals(likesDTO.getUserId(), retrievedLikes.getUserId());
        assertEquals(likesDTO.getReviewId(), retrievedLikes.getReviewId());
    }

    @Test
    void countLikesByReviewId() {
        User user = userService.getUser("cocoa389", "1234").toEntity();
        Movie movie = movieService.getMovie(102).toEntity();
        RatingDTO ratingDTO = RatingDTO.builder().ratingScore(5).build();
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewContent("Great movie!")
                .reviewDate(new Date())
                .userId(user.getUserId())
                .movieId(movie.getMovieId())
                .ratingScore(ratingDTO.getRatingScore())
                .build();

        reviewService.insertReview(reviewDTO, ratingDTO);

        LikesDTO likesDTO1 = LikesDTO.builder()
                .userId(user.getUserId())
                .reviewId(reviewDTO.getReviewId())
                .build();
        likesService.insertLikes(likesDTO1);

        LikesDTO likesDTO2 = LikesDTO.builder()
                .userId(user.getUserId())
                .reviewId(reviewDTO.getReviewId())
                .build();
        likesService.insertLikes(likesDTO2);

        int count = likesService.countLikesByReviewId(reviewDTO.getReviewId());
        assertEquals(2, count);
    }
}
