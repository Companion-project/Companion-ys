package com.example.companion.mapper;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    //상품입고번호 자동부여를 같이 사용하도록 구현
    //@Param을 통해 여러 인자를 사용할 수 있도록 설정(myBatis기본값은 인자 1개만 사용)
    public String incomingAndGoodsAutoNum(@Param("tableName") String tableName,
                                          @Param("columnName") String ColumnName,
                                          @Param("sep") String sep,
                                          @Param("seq") Integer seq);

    public int goodsInsert(GoodsDTO dto);
    public List<GoodsDTO> allSelect(StartEndPageDTO sepDTO);
    public int goodsCount(String searchWord);
}
