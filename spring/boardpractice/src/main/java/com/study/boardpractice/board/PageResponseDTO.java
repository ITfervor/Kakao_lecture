package com.study.boardpractice.board;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO <DTO, EN>{
    private List<DTO> dtoList;
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

}
