package com.study.loginstudy.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.study.loginstudy.vo.UserVo;

@Mapper
public interface UserMapper {

    void saveUser(UserVo userVo);

}
