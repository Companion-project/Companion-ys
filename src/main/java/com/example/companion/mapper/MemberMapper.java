package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void memberInsert(MemberDTO dto);
    String memberAutoNum();
    public List<MemberDTO> selectAll(StartEndPageDTO sepDTO);
    public int memberCount(String searchWord);
    public int membersDelete(@Param("membersNum") String[] memDels);
    public MemberDTO memberSelectOne(String memberNum);
    public int memberUpdate(MemberDTO dto);
    public int memberDelete(String memberNum);

}
