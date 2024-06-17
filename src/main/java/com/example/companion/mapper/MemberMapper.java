package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    void memberInsert(MemberDTO dto);
    String memberAutoNum();
}
