package com.kakao.board1.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//DB의 테이블과 매칭될 Class파일들이 모여있음.(Entity클래스 라고도함.)
@MappedSuperclass // 테이블 생성을 하지 않는 Entity
//JPA동작을 감시
@EntityListeners(value={AuditingEntityListener.class})
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name="regdate" , updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate" )
    private LocalDateTime modDate;
}
