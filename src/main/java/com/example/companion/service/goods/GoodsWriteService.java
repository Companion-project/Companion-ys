package com.example.companion.service.goods;

import com.example.companion.command.GoodsCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.UUID;

@Service
public class GoodsWriteService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    public void execute(GoodsCommand goodsCommand, HttpSession session){
        GoodsDTO dto = new GoodsDTO();
        dto.setGoodsContent(goodsCommand.getGoodsContent());
        dto.setGoodsName(goodsCommand.getGoodsName());
        dto.setGoodsNum(goodsCommand.getGoodsNum());
        dto.setGoodsPrice(goodsCommand.getGoodsPrice());
        dto.setDeliveryCost(goodsCommand.getDeliveryCost());

        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");

        String empId = auth.getUserId();
        String empNum = employeeMapper.getEmpNum(empId);
        dto.setEmpNum(empNum);

        //파일 로직 추가

        //파일저장경로 resource/static/upload
        URL resource = getClass().getClassLoader().getResource("static/upload");
        String fileDir = resource.getFile();
        //대문이미지
        MultipartFile mf = goodsCommand.getGoodsMainStore(); //이미지객체
        String originalFile = mf.getOriginalFilename(); //업로드시 파일명
        String extension = originalFile.substring(originalFile.lastIndexOf(".")); //확장자
        String storeName = UUID.randomUUID().toString().replace("-",""); //임의의 이름 부여
        String storeFileName = storeName + extension; //새로운파일명
        //static/upload에 새로운파일명으로 파일 저장
        File file = new File(fileDir + "/" + storeFileName);
        try {
            mf.transferTo(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        //dto에 파일명 저장
        dto.setGoodsMainStore(storeFileName); //static/upload에 저장된 이름
        dto.setGoodsMainStoreImg(originalFile); //upload할 대 사용된 이름

        //파일 존재 유무 확인
        if(!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()){
            String originalTotal = "";
            String storeTotal = "";

            for(MultipartFile mtf : goodsCommand.getGoodsImages()){
                originalFile = mtf.getOriginalFilename();
                extension = originalFile.substring(originalFile.lastIndexOf("."));
                storeName = UUID.randomUUID().toString().replace("-","");
                storeFileName = storeName + extension;

                file = new File(fileDir + "/" + storeFileName);
                try {
                    mtf.transferTo(file);
                } catch (Exception e){
                    e.printStackTrace();
                }
                //파일명을 하나의 문자열로 만들어 준다
                originalTotal += originalFile + "-";
                storeTotal += storeFileName + "-";
            }
            //dto에 저장
            dto.setGoodsImages(storeTotal);
            dto.setGoodsImagesImg(originalTotal);
        } // 파일저장경로: user1\IdeaProjects\Companion-ys\src\main\resources\static\ upload
        // 주의점: src에 저장되는 것이 아닌 bin에 저장 -> 프로젝트에서 보이지 않음!
        goodsMapper.goodsInsert(dto);
    }
}
