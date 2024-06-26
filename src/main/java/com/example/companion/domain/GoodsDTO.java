package com.example.companion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 멤버 필드 생성자
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

    //DB에 파일명 저장을 위한 부분
    String goodsMainStore;
    String goodsMainStoreImg;
    String goodsImages;
    String goodsImagesImg;

    //생성자 생성
/* MyBatis에서 생성자를 사용할 때 int타입은 오류 발생
    타입 오류를 발생하지 않으려면 Integer를 사용해야 함!

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

 */
}
