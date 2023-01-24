package com.kakao.board1.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@Data
@AllArgsConstructor
public class PageRequestDTO {
    //페이지 처리를 위한 속성

    private int page;
    private int size;

    //검색 관련 속성
    private String type;

    private String keyword;

    //page와 size값이 없을때 사용할 기본값 설정을 위한 생성자
    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }
    //page와 size를 가지고 Pageable객체를 생성해주는 메서드
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }


}
