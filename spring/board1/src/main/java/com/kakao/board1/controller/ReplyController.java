package com.kakao.board1.controller;

import com.kakao.board1.board.ReplyDTO;
import com.kakao.board1.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies") //공통 URL설정
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping(value="/board/{bno}")
    public ResponseEntity<List<ReplyDTO>>
        getByBoard(@PathVariable("bno") Long bno){
        log.info("bno : " + bno);
        //앞에 서비스 찍고 뒤에는 상태 표기
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);

    }

    @PostMapping("")
    //댓글 추가 요청 처리
    public ResponseEntity<Long> register(
            @RequestBody ReplyDTO replyDTO
    ){
        log.info(replyDTO);
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("Rno:" + rno);
        replyService.remove(rno);
        return new ResponseEntity(rno + "삭제",HttpStatus.OK);
    }

    @PutMapping("/{rno}")
    public ResponseEntity<Long> Modify(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Long rno = replyService.modify(replyDTO);
        return new ResponseEntity<>(rno ,HttpStatus.OK);
    }
}
