package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //자바에서 메서드는 기본적으로 public이므로 생략했음.
    void memberInsert(MemberDTO dto);
    String memberAutoNum();
    List<MemberDTO> selectAll(StartEndPageDTO sepDTO);
    int memberCount(String searchWord);
}
