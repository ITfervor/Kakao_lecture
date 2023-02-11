package com.study.loginstudy.vo;

import lombok.Data;

@Data//@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
//  어노테이션을 한꺼번에 설정해주는 어노테이션
public class UserVo {
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userAuth;
    private String appendDate;
    private String updateDate;

}
