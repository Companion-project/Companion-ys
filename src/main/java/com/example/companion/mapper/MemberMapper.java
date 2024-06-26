package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    void memberInsert(MemberDTO dto);
    String memberAutoNum();
    public List<MemberDTO> selectAll(String searchWord);
}
