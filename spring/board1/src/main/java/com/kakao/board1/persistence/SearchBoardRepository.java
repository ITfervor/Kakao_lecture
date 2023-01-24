package com.kakao.board1.persistence;

import com.kakao.board1.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface SearchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
