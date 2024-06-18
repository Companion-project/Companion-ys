package com.example.companion.service.member;

import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.MemberMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MemberListService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    StartEndPageService startEndPageService;
    String searchWord;

    public void execute(Model model, String searchWord, int page){

        if(searchWord != null) {
            this.searchWord = searchWord.trim();
        }

        StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
        List<MemberDTO> list = memberMapper.selectAll(sepDTO);
        int count = memberMapper.memberCount(this.searchWord);
        startEndPageService.execute(page, count, model, list, this.searchWord);
    }
}
