package com.example.companion.service.goods;

import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsDeleteService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(String goodsNum){
        goodsMapper.goodsDelete(goodsNum);
    }
}
