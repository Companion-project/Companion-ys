package com.example.companion.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

//command멤버필드는 html의 input이름과 동일하게 생성함
//유효성검사를 위해 validated부여
@Data //lombok사용 -> setter/getter
public class GoodsCommand {

    String goodsNum;
    @NotEmpty(message = "이름을 입력해주세요.")
    String goodsName;
    @NotNull(message = "가격을 입력해주세요.")
    Integer goodsPrice;
    @NotNull(message = "배송비를 입력해주세요.")
    Integer deliveryCost;
    @NotEmpty(message = "설명을 입력해주세요.")
    String goodsContent;

    //파일 받을 객체 추가
    MultipartFile goodsMainStore;
    MultipartFile goodsImages[];

    Integer visitCount;
    String empNum;
    Date goodsRegist;
    String updateEmpNum;
    Date goodsUpdateDate;
}
