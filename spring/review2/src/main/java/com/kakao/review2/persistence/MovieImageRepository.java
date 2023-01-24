package com.kakao.review2.persistence;

import com.kakao.review2.domain.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}


