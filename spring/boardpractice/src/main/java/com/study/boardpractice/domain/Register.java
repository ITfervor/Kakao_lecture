package com.study.boardpractice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "password")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String Userid;
    private String Password;
    private String writerEmail;
    private String writerName;

}
