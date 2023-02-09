package com.study.boardpractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tbl_member")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseEntity{
    @Id
    private String email;
    private String password;
    private String name;

}
