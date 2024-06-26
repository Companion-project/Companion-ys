package com.example.companion.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class GoodsDTO {
    String goodsNum;
    String goodsName;
    Integer goodsPrice;
    Integer deliveryCost;
    String goodsContent;
    String empNum;

    Integer visitCount;
    Date goodsRegist;
    String updateEmpNum;
    Date goodsUpdateDate;

//    String goodsMainStore;
//    String goodsMainStoreImg;
//    String goodsImages;
//    String goodsImagesImg;

    //생성자 생성


    public GoodsDTO(String goodsNum, String goodsName, Integer goodsPrice,
                    Integer deliveryCost, String goodsContent, String empNum,
                    Integer visitCount, Date goodsRegist, String updateEmpNum,
                    Date goodsUpdateDate) {
        this.goodsNum = goodsNum;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.deliveryCost = deliveryCost;
        this.goodsContent = goodsContent;
        this.empNum = empNum;
        this.visitCount = visitCount;
        this.goodsRegist = goodsRegist;
        this.updateEmpNum = updateEmpNum;
        this.goodsUpdateDate = goodsUpdateDate;
    }
}
