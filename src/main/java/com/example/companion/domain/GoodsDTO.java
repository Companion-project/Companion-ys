package com.example.companion.domain;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsDTO {
    String goodsNum;
    String goodsName;
    Integer goodsPrice;
    Integer deliveryCost;
    String goodsContent;
    String empNum;

    int visitCount;
    Date goodsRegist;
    String updateEmpNum;
    Date goodsUpdateDate;
}
