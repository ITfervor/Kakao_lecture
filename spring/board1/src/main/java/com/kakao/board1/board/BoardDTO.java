package com.kakao.board1.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//DTO 패키지는 각 계층간 데이터 교환을 위한 객체 이다.
//DB로 부터 데이터를 얻어 서비스, 컨트롤러 등으로 보낼때 사용한다.

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;

    private String writerEmail;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private int replyCount;
}
