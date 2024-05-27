package service;

import domain.Likes;
import dto.LikesDTO;
import repository.LikesRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LikesService {

    //서비스 계층도 싱글톤으로 변경
    private static final LikesService likesService = new LikesService();
    private final LikesRepository likesRepository = LikesRepository.getInstance();

    public static LikesService getInstance() {
        return likesService;
    }

    private LikesService() {}

    public void insertLikes(LikesDTO likesDTO) {
        Likes likes = likesDTO.toEntity();
        likesRepository.save(likes);
    }

    public LikesDTO getLikes(int likesId) {
        Likes likes = likesRepository.findOne(likesId);
        if (likes == null) {
            throw new NullPointerException("Likes not found");
        }
        return LikesDTO.fromEntity(likes);
    }

    public List<LikesDTO> getAllLikes() {
        List<Likes> likesList = likesRepository.findAll();
        return likesList.stream()
                .map(LikesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteLikes(int likesId) {
        Likes likes = likesRepository.findOne(likesId);
        if (likes != null) {
            likesRepository.delete(likes);
        }
    }

    public int countLikesByReviewId(int reviewId) {
        return likesRepository.countLikesByReviewId(reviewId);
    }
}
