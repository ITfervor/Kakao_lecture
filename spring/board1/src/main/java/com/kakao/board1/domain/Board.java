package com.kakao.board1.domain;


import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")//toString을 만들때 writer의 toString호출
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) //처음에는 가져오지않고 사용할떄 가져온다.
    private Member writer;

    //title 수정하는 메서드
    public void changeTitle(String title){
//        if(title == null || title.trim().length() == 0) {
//            this.title = "무제";
//            return;
//        }
            this.title = title;
    }

    //content를 수정하는 메서드
    public void changeContent(String content){
        this.content = content;
    }

}
