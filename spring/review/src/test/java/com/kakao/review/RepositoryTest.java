package com.kakao.review;

import com.kakao.review.domain.Member;
import com.kakao.review.domain.Movie;
import com.kakao.review.domain.MovieImage;
import com.kakao.review.domain.Review;
import com.kakao.review.persistence.MemberRepository;
import com.kakao.review.persistence.MovieImageRepository;
import com.kakao.review.persistence.MovieRepository;
import com.kakao.review.persistence.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void insertMovie(){
        //closed뒤에 있는 숫자는 i가 1부터 100까지 생성을 뜻함.
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie..." + i)
                    .build();
            movieRepository.save(movie);

            int count = (int)(Math.random()*5) + 1;
            for(int j=0; j<count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j+ ".jpg" )
                        .build();
                movieImageRepository.save(movieImage);
            }
        });
    }

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .email("r"+ i + "@gmail.com")
                    .pw("2222")
                    .nickname("reviewer" + i)
                    .build();
            memberRepository.save(member);
        });
    }
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReview() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            // 영화 번호
            Long mno = (long) (Math.random() * 100) + 1;
            // 회원 번호
            Long mid = (long) (Math.random() * 100) + 1;

            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("영화 느낌…." + i)
                    .build();

            reviewRepository.save(review);
        });
    }

    @Test
    //Join연습
    public void joinTest(){
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"mno"));

        Page<Object []> result =
                movieRepository.getList(pageable);
        for(Object [] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void detailTest(){
        List<Object []> list = movieRepository.getMovieWithAll(3L);
        for (Object []ar : list){
            System.out.println(Arrays.toString(ar));
        }
    }
    @Test
    //@Transactional
    public void getReviews(){
        Movie movie = Movie.builder().mno(96L).build();

        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            System.out.println(review.getMember().getEmail());
        });

    }
    @Test
    @Transactional
    @Commit
    public void deleteByMember(){
        Member member = Member.builder().mid(89L).build();
        reviewRepository.deleteByMember(member);
    }

    @Test
    @Transactional
    @Commit
    public void updateByMember(){
        reviewRepository.updateByMember(58L);
    }


}
