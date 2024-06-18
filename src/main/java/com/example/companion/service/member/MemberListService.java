package com.example.companion.service.member;

import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MemberListService {

    @Autowired
    MemberMapper memberMapper;

    public void execute(Model model, String searchWord, int page){

        int limit=10;
        int startRow = ((page-1)*limit)+1;
        int endRow = startRow + limit -1;

        StartEndPageDTO sepDTO = new StartEndPageDTO();
        sepDTO.setEndRow(endRow);
        sepDTO.setSearchWord(searchWord);
        sepDTO.setStartRow(startRow);
        List<MemberDTO> list = memberMapper.selectAll(sepDTO);
        int count = memberMapper.memberCount(searchWord);
        int limitPage = 10;
        int startPage = (int)((double)page/limitPage+0.95-1)*limitPage+1;
        int endPage = startPage+limitPage-1;
        int maxPage = (int) ((double) count/limit+0.95);
        if(maxPage < endPage) endPage = maxPage;

        model.addAttribute("dtos", list);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("page", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("count", count);
        model.addAttribute("maxPage", maxPage);
    }
}
