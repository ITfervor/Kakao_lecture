package com.kakao.board1;

import com.kakao.board1.domain.Board;
import com.kakao.board1.domain.Member;
import com.kakao.board1.domain.Reply;
import com.kakao.board1.persistence.BoardRepository;
import com.kakao.board1.persistence.MemberRepository;
import com.kakao.board1.persistence.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    //회원 데이터 삽입
    public void insertMember(){
        for(int i =1; i<=100; i++){
            Member member = Member.builder()
                    .email("user"+ i + "@kakao.com")
                    .password("1133")
                    .name("USER"+i)
                    .build();
            memberRepository.save(member);
        }
    }
    @Test
    public void insertBoard(){
        for(int i =1; i<=100; i++){
            Member member = Member.builder()
                    .email("user"+ i + "@kakao.com")
                    .build();
            Board board = Board.builder()
                    .title("제목..."+i)
                    .content("내용..."+i)
                    .writer(member)
                    .build();
           boardRepository.save(board);
        }

    }
//    @Test
//    public void insertReply(){
//        for(int i = 0; i<=300; i++){
//            long bno = (long)(Math.random() * 100) + 1;
//            Board board = Board.builder().bno(bno).build();
//            Reply reply = Reply.builder()
//                    .text("댓글..." + i)
//                    .board(board)
//                    .replyer("guest")
//                    .build();
//            replyRepository.save(reply);
//        }
//    }

    @Test
    @Transactional //데이터베이스 종료없이 끝까지 수행하기 위한 애노테이션
    //게시글 1개를 가져오는 메서드
    public void readBoard(){
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    @Transactional
    //게시글 1개를 가져오는 메서드
    public void readReply(){
        Optional <Reply> result = replyRepository.findById(3001L);
        Reply reply = result.get();
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    //Board 테이터를 가져올 떄 Writer데이터도 가져오기
    @Test
    public void joinTest1(){
        Object result = boardRepository.getBoardWithWriter(100L);
        //결과가 배열인거 같아서
        System.out.println(result);
        Object [] ar = (Object []) result;

        System.out.println((Arrays.toString(ar)));
        Board board = (Board) ar[0];
        Member member = (Member)ar[1];
    }

    @Test
    public void testJoin2(){
        List<Object []> result = boardRepository.getBoardWithReply(100L);
        for (Object []ar :result){
            System.out.println(Arrays.toString(ar));
        }
    }
    @Test
    public void testJoin3(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Object []> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row-> {
            Object [] ar= (Object []) row;
            //System.out.println(Arrays.toString(ar));
            Board b = (Board) ar[0];
            Member m = (Member) ar[1];
            Long c = (Long) ar[2];
            System.out.println(m);

            System.out.println(c);

        });


    }
    @Test
    public void testSearch1(){
        boardRepository.search1();
    }

    @Test
    public void testSearch(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));
        //type에 따라 어디 조회할지 정해짐
        Page<Object[]> result = boardRepository.searchPage("t", "asd", pageable);
        System.out.println(result.getContent());

        for(Object [] row : result.getContent() ){
            System.out.println(Arrays.toString(row));
        }
    }

}
