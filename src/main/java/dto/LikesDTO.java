package dto;

import domain.Likes;
import domain.Review;
import domain.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LikesDTO {
    private int likesId;
    private int userId;
    private int reviewId;

    @Builder
    public LikesDTO(int likesId, int userId, int reviewId) {
        this.likesId = likesId;
        this.userId = userId;
        this.reviewId = reviewId;
    }

    public static LikesDTO fromEntity(Likes likes) {
        return LikesDTO.builder()
                .likesId(likes.getLikesId())
                .userId(likes.getUser().getUserId())
                .reviewId(likes.getReview().getReviewId())
                .build();
    }

    public Likes toEntity() {
        return Likes.builder()
                .likesId(this.likesId)
                .user(User.builder().userId(this.userId).build())
                .review(Review.builder().reviewId(this.reviewId).build())
                .build();
    }
}