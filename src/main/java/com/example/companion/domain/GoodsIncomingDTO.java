package com.example.companion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor //생성자
@NoArgsConstructor //기본생성자
public class GoodsIncomingDTO {
    String incomingNum;
    String goodsNum;
    Integer incomingQty;
    Date incomingDate;
    Date productionDate;
    Integer incomingPrice;
    String empNum;
}
