package com.study.loginstudy.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Getter
@MappedSuperclass //테이블로 매핑하지 않고 자식 클래스(엔티티) 에게 매핑정보를 상속하기 위한 어노테이션이다.
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    @CreatedDate //속성을 추가하지않으면 수정시 해당 값은 null 이 된다.
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate //엔티티가 수정될때 수정일자를 주입하는 어노테이션
    private LocalDateTime modifiedDate;
}