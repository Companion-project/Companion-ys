package com.example.companion.service.goods;

import com.example.companion.command.FileCommand;
import com.example.companion.command.GoodsCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsUpdateService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    public void execute(GoodsCommand goodsCommand, HttpSession session,
                        BindingResult result, Model model){
        GoodsDTO dto = new GoodsDTO();
        dto.setGoodsContent(goodsCommand.getGoodsContent());
        dto.setGoodsName(goodsCommand.getGoodsName());
        dto.setGoodsNum(goodsCommand.getGoodsNum());
        dto.setGoodsPrice(goodsCommand.getGoodsPrice());
        dto.setDeliveryCost(goodsCommand.getDeliveryCost());
        //로그인 정보를 이용해 수정한 직원의 정보를 가져옴
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String empId = auth.getUserId();
        String empNum = employeeMapper.getEmpNum(empId);
        dto.setUpdateEmpNum(empNum);

        //파일수정코드 추가

        //파일삭제정보
        List<FileCommand> list = (List<FileCommand>)session.getAttribute("fileList");
        //상품정보
        GoodsDTO goodsDTO = goodsMapper.selectOne(goodsCommand.getGoodsNum());
        //삭제할 파일과 업로드할 파일의 디렉토리 정보
        URL resource = getClass().getClassLoader().getResource("static/upload");
        String fileDir = resource.getFile();
        System.out.println(fileDir);
        //파일 정보 가져올 변수 지정
        MultipartFile mf;
        String originalFile;
        String extension;
        String storeName;
        String storeFileName;
        File file;

        if(!goodsCommand.getGoodsMainStore().getOriginalFilename().isEmpty()){
            mf = goodsCommand.getGoodsMainStore();
            originalFile = mf.getOriginalFilename();
            extension = originalFile.substring(originalFile.lastIndexOf("."));
            storeName = UUID.randomUUID().toString().replace("-","");
            storeFileName = storeName + extension;

            dto.setGoodsMainStore(storeFileName);
            dto.setGoodsMainStoreImg(originalFile);

            file = new File(fileDir + "/" + storeFileName);
            try {
                mf.transferTo(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            if(list != null){
                for(FileCommand fileCommand : list){
                    if(fileCommand.getStoreFile().equals(goodsDTO.getGoodsMainStore())){
                        result.rejectValue("goodsMainStore", "error");
                        model.addAttribute("error", "대문이미지를 선택해주세요.");
                        model.addAttribute("goodsCommand", goodsDTO);
                        session.removeAttribute("fileList");
                        return;

                    }
                }
            }
        }
        List<String> goodsImages = new ArrayList<String>();
        List<String> goodsOrgImages = new ArrayList<String>();
        if(goodsDTO.getGoodsImages() != null){
            String[] images = goodsDTO.getGoodsImagesImg().split("-");
            for (String img : images){
                goodsImages.add(img);
            }
            for (String img : images) {
                goodsOrgImages.add(img);
            }
            //session에 있는 값을 list에서 삭제처리
            if(list != null){
                for(FileCommand fileCommand : list){
                    for(String str : goodsImages){
                        //삭제할 파일 정보 -> DB에서 제거
                        if(fileCommand.getStoreFile().equals(str)) {
                            goodsImages.remove(fileCommand.getStoreFile());
                            goodsOrgImages.remove(fileCommand.getOrgFile());
                            break;
                        }
                    }
                }
            }
        }
        //설명이미지
        //DB에 가져온 수정된 파일 정보 추가처리
        String originalTotal = "";
        String storeTotal = "";
        if (!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()){
            for (MultipartFile mtf : goodsCommand.getGoodsImages()){
                originalFile = mtf.getOriginalFilename();
                extension = originalFile.substring(originalFile.lastIndexOf("."));
                storeName = UUID.randomUUID().toString().replace("-","");
                storeFileName = storeName + extension;

                file = new File(fileDir + "/" + storeFileName);
                try {
                    mtf.transferTo(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //여러개의 파일명 하나로 묶음
                originalTotal += originalFile + "-";
                storeTotal += storeFileName + "-";
            }
        }
        //수정된 정보 DB에 추가
        for(String img : goodsImages){
            storeTotal += img + "-";
        }
        for (String img : goodsOrgImages){
            originalTotal += img + "-";
        }

        dto.setGoodsImages(storeTotal);
        dto.setGoodsImagesImg(originalTotal);

        int i = goodsMapper.goodsUpdate(dto);
        //DB에 내용 수정완료 -> 파일 삭제처리
        if(i>0){
            if(list != null){
                for (FileCommand fileCommand : list){
                    file = new File(fileDir + "/" + fileCommand.getStoreFile());
                    if(file.exists())
                        file.delete();
                }
            }
        }
        //세션삭제처리
        session.removeAttribute("fileList");
    }
}
