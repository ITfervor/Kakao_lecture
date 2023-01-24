package com.kakao.board1.service;

import com.kakao.board1.board.PageRequestDTO;
import com.kakao.board1.board.PageResponseDTO;
import com.kakao.board1.domain.Board;
import com.kakao.board1.domain.Member;
import com.kakao.board1.board.BoardDTO;

public interface BoardService {
   //게시글 등록 요청
     Long register(BoardDTO dto);

     //게시글 상세보기
     BoardDTO get(Long bno);

     Long modify(BoardDTO dto);


    //게시글 목록 보기
    PageResponseDTO<BoardDTO, Object[]> getList(
            PageRequestDTO pageRequestDTO);

    //게시글 번호를 가지고 댓글을 삭제
    void removeWithReplies(Long bno);


    //DTO -> Entity로 변환해주는 메서드

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;

    }


    //Entity -> DTO로 변환해주는 메서드
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount){
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
        return dto;

    }
}
