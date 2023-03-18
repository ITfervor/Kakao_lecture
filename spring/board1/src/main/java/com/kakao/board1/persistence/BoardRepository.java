package com.kakao.board1.persistence;

import com.kakao.board1.domain.Board;


import java.awt.print.Pageable;
import java.util.List;
//Repository패키지는 Entity에 의해 생성된 DB에 접근하는 메서드들을 사용하기 위한 interface파일들이 모여있는 패키지이다.
//보통 JpaRepository를 상속 받음으로써 기본적인 CRUD동작이 모두 가능해진다.
//JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK의 타입>
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository{
    //board 데이터를 가져올 때 writer 정보도 가져오는 메서드
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    public Object getBoardWithWriter(@Param("bno") Long bno);

    //글 번호를 받아서 Board와 그리고 Board와 관련된 Reply 정보 찾아오기
    //Board한개에 여러개의 Reply가 존재
    //Board와 Reply를 결합한 형태의 목록으로 리턴
    @Query("select b,r from Board b left join Reply r on r.board = b where b.bno=:bno")
    List<Object []> getBoardWithReply(@Param("bno") Long bno);

    //게시글 목록과 작성자 정보 그리고 댓글의 개수를 가져오는 메서드
    @Query("select b, w, count(r) from Board b left join b.writer w left join " +
            "Reply r on r.board = b group by b")
    Page<Object []> getBoardWithReplyCount(Pageable pageable);

    //글번호를 가지고 데이터를 찾아오는 메서드 - 상세보기
    @Query("select b, w, count(r) from Board b left join b.writer w left outer join Reply r on r.board = b where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
