package com.kakao.board1.persistence;

import com.kakao.board1.domain.Board;
import com.kakao.board1.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //게시글을 이용해서 데이터 찾아오기 모든 댓글을 찾으므로 LIST형식
    List<Reply> findByBoardOrderByRno(Board board);

    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bnt);
}
