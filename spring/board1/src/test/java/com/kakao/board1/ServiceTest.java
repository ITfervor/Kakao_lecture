package com.kakao.board1;
//
//import com.kakao.board1.board.BoardDTO;
//import com.kakao.board1.service.BoardService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class ServiceTest {
//    @Autowired
//    private BoardService boardService;
//
//    //등록테스트
//    @Test
//    public void registerTest(){
//        BoardDTO dto = BoardDTO.builder()
//                .title("등록테스트")
//                .content("등록을 테스트한다.")
//                .writerName("user12@kakao.com")
//                .build();
//        Long bno = boardService.register(dto);
//        System.out.println(bno);
//    }
//
//}

import com.kakao.board1.board.BoardDTO;
import com.kakao.board1.board.PageRequestDTO;
import com.kakao.board1.board.PageResponseDTO;
import com.kakao.board1.board.ReplyDTO;
import com.kakao.board1.domain.Board;
import com.kakao.board1.domain.Reply;
import com.kakao.board1.persistence.ReplyRepository;
import com.kakao.board1.service.BoardService;
import com.kakao.board1.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ReplyRepository replyRepository;

    //등록 테스트
    @Test
    public void registerTest(){
        BoardDTO dto = BoardDTO.builder()
                .title("등록 테스트")
                .content("등록을 테스트합니다.")
                .writerEmail("user33@kakao.com")
                .build();
        Long bno = boardService.register(dto);
        System.out.println(bno);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResponseDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        System.out.println(result);
    }

    @Test
    public void testGet(){
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }

    @Test
    public void testDelete(){
        boardService.removeWithReplies(110L);
    }

    @Test
    public void testUpdate(){
        BoardDTO dto = BoardDTO.builder()
                .bno(99L)
                .title("제목변경")
                .content("내용변경")
                .build();

        System.out.println(boardService.modify(dto));

    }

    @Test
    public void testListReply(){
        List<Reply> replyList =
                replyRepository.findByBoardOrderByRno(
                        Board.builder().bno(27L).build()
                );
        replyList.forEach(
                reply -> System.out.println(reply));

    }

    @Autowired
    ReplyService replyService;

    @Test
    public void testGetList(){
        //게시글 번호를 이용해서 댓글을 가져오기
        List <ReplyDTO> list = replyService.getList(65L);
        list.forEach(dto -> System.out.println(dto));
     }

    @Test
    public void insertReply(){
        ReplyDTO dto = ReplyDTO.builder()
                .text("댓글 삽입 테스트")
                .replyer("user1@kakao.com")
                .bno(27L)
                .build();
        System.out.println(replyService.register(dto));
    }
}
