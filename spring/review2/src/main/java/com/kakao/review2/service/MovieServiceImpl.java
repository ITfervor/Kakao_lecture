package com.kakao.review2.service;

import com.kakao.review2.domain.Movie;
import com.kakao.review2.domain.MovieImage;
import com.kakao.review2.dto.MovieDTO;
import com.kakao.review2.persistence.MovieImageRepository;
import com.kakao.review2.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Override
    @Transactional
    public Long register(MovieDTO movieDTO) {
        log.warn("movieDTO:" + movieDTO);

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        //영화 와 영화 이미지 정보 찾아오기
        Movie movie = (Movie)entityMap.get("movie");
        List<MovieImage> movieImageList =
                (List<MovieImage>) entityMap.get("imgList");
        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });
        return movie.getMno();
    }
}
