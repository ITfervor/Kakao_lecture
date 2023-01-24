package com.kakao.board1.service;

import com.kakao.board1.board.PageRequestDTO;
import com.kakao.board1.board.PageResponseDTO;
import com.kakao.board1.domain.Board;
import com.kakao.board1.board.BoardDTO;
import com.kakao.board1.domain.Member;
import com.kakao.board1.persistence.BoardRepository;
import com.kakao.board1.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    //final: 읽기 전용이므로 밑의 구문은 메서드를 구현하는 구문이다.
    // static이 있다면 객체를 공유하겠다는 뜻으로 재할당이 가능하지만
    // static이 없다면 재할당이 불가능하지만 메서드 별로 호출할 때마다 새로이 값이 할당한다.
    private final BoardRepository boardRepository;

    @Transactional
    public Long register(BoardDTO dto){
        log.info("Service:" + dto);
        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }
    public PageResponseDTO<BoardDTO, Object[]> getList(
            PageRequestDTO pageRequestDTO){
        log.info(pageRequestDTO);

        //Entity를 DTO로 변경하는 람다 인스턴스 생성
        Function<Object [], BoardDTO> fn = (
                en -> entityToDTO((Board)en[0] , (Member)en[1], (Long)en[2]));

        //목록 보기 요청처리
        Page<Object []> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResponseDTO<>(result, fn);
    }

    public BoardDTO get(Long bno){
        Object result = boardRepository.getBoardByBno(bno);
        Object [] arr = (Object[]) result;
        return entityToDTO((Board)arr[0] , (Member)arr[1], (Long)arr[2]);
    }

    private final ReplyRepository replyRepository;

    @Transactional
    public void removeWithReplies(Long bno){
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Transactional
    public Long modify(BoardDTO dto){
        //JPA에서는 삽입과 수정 메서드가 동일
        //upsert(있으면 수정 없으면 삽입)를 하고자 하는 경우는 save를 호출하면 되지만
        //update 를 하고자 하면 데이터가 있는 지 확인한 후 수행하는것이 좋다.
        if(dto.getBno() == null){
            return 0L;
        }

        //데이터 존재여부를 확인
        Optional<Board> board = boardRepository.findById(dto.getBno());
        //존재하는 경우
        if(board.isPresent()){
            board.get().changeTitle(dto.getTitle());
            board.get().changeTitle(dto.getContent());
            boardRepository.save(board.get());
            return board.get().getBno();
        }else{
            return 0L;
        }
    }

}

