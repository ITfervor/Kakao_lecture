package com.kakao.review2.persistence;

import com.kakao.review2.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //영화정보를 가지고 영화 이미지 정보와 리뷰개수 및 grade의 평균을 구해주는 메서드 페이지 단위로 구하기
    @Query("select m, mi, avg(coalesce(r.grade, 0)) , count(distinct r.reviewnum) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m group by m") //Movie는 테이블 이름이 아니고 Entity명이다.
    public Page<Object []> getList(Pageable pageable);

    //특정 영화정보를 가지고 영화 이미지 정보와 리뷰개수 및 grade의 평균을 구해주는 메서드 페이지 단위로 구하기
    @Query("select m, mi, avg(coalesce(r.grade , 0)), count(distinct r.reviewnum) from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m where m.mno = :mno " +
            "group by mi")
    List<Object []> getMovieWithAll(@Param("mno") Long mno);
}
