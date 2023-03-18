package com.study.hongboard.api;

import com.study.hongboard.dto.ArticleForm;
import com.study.hongboard.entity.Article;
import com.study.hongboard.repository.ArticleRepository;
import com.study.hongboard.service.ArticleService;
import com.study.hongboard.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired //DI
    private ArticleService articleService;





//
//    //GET
//    @GetMapping("/api/articles")
//    public List<Article> index(){
//        return articleRepository.findAll();
//    }
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

//
//
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);

    }

//
//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){

        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, //Response Entity는 상태코드를 싣어서 보내준다.
//                                          @RequestBody ArticleForm dto){
//        //1.수정용 엔티티생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString()); //괄호 차례식 하나하나 대입
//
//        //2.대상인티티를 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //3.잘못된 요청처리(대상이 없거나, id가 다른 경우)
//        if (target == null || id != article.getId()){
//            //400, 잘못된 요청응답!
//            log.info("잘못된 요청! id: {}, article: {}",id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//
//        //4.업데이트 및 정상 응답(200)
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//
//    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
