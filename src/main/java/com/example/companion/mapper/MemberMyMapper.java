package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMyMapper {
    public MemberDTO memberInfo(String memberId);
}
