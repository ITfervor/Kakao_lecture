package com.example.springbootjpab.persistence;

import com.example.springbootjpab.dto.MemoDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoMapper {
    @Select("Select * from tbl_memo")
    public List<MemoDTO> listMemo();
}
