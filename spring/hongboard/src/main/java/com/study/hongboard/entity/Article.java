package com.study.hongboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@Entity //DB가 해당객체를 인식 (해당 클래스로 테이블을 만든다)
@NoArgsConstructor //기본생성자 추가
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;


//    public Article() {
//
//    } //기본생성자


}
