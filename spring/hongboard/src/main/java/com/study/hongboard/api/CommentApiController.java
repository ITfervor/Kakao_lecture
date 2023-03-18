package com.study.hongboard.api;

import com.study.hongboard.dto.CommentDto;
import com.study.hongboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스에세 위임
        CommentDto updateDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    //댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스에게 위임
        CommentDto deleteDto = commentService.delete(id);

        //결과응덥
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }



}