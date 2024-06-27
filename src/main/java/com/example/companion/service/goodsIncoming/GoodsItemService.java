package com.example.companion.service.goodsIncoming;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.GoodsMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsItemService {

    @Autowired
    GoodsMapper goodsMapper; //상품정보 가져옴

    @Autowired
    StartEndPageService startEndPageService;

    String searchWord;

    public Map<String , Object> execute(String searchWord, int page){
        //searchWord 가 null일 경우
        if(searchWord != null){
            this.searchWord = searchWord.trim();
        }
        Map<String, Object> map = new HashMap<String, Object>();

        StartEndPageDTO sepDTO = startEndPageService.execute(page,this.searchWord);
        List<GoodsDTO>list = goodsMapper.allSelect(sepDTO);

        int count = goodsMapper.goodsCount(this.searchWord);
        int limit=10;
        int limitPage=10;
        int startPage = (int)((double)page/limitPage + 0.95 - 1) * limitPage + 1;
        int endPage = startPage + limitPage - 1;
        int maxPage = (int)((double)count / limit + 0.95);
        if(maxPage<endPage)endPage = maxPage;
        //model대신 map에 저장
        map.put("dtos",list);
        map.put("searchWord", this.searchWord);
        map.put("page",page);
        map.put("startPage",startPage);
        map.put("endPage",endPage);
        map.put("count",count);
        map.put("maxPage",maxPage);
        return map;
    }
}
