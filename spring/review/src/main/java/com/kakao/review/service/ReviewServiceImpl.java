package com.kakao.review.service;

import com.kakao.review.domain.Movie;
import com.kakao.review.domain.Review;
import com.kakao.review.dto.ReviewDTO;
import com.kakao.review.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getList(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        return result.stream().map(review -> entityToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
            Review review = dtoToEntity(reviewDTO);
            reviewRepository.save(review);
            return review.getReviewnum();
    }
    //수정과 삽입은 동일하다.
    @Override
    public Long modify(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
        return review.getReviewnum();
    }
    //deleteById를 사용하면 서비스 로직에서 메소드를 하나만 사용해도 조회와 삭제가 다 된다는 장점이있다.
    @Override
    public Long remove(Long rnum) {
        reviewRepository.deleteById(rnum);
        return rnum;
    }
}
