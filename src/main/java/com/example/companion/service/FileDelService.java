package com.example.companion.service;

import com.example.companion.command.FileCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileDelService {
    public String execute(FileCommand fileCommand, HttpSession session){
        String num = ""; //저장할 파일인지 아닌지를 정해줄 문자열 변수 선언
        Boolean newFile = true; //새로운 상품인지 아닌지를 확인하기 위한 변수
        //세션에 임시로 삭제파일정보 저장 -> 여러개의 파일의 경우를 고려해 List에 저장
        List<FileCommand> list = (List<FileCommand>) session.getAttribute("fileList");
        //첫 상품의 경우 세션이 존재하지 않는다 -> list
        //세션이 없으면 list부터 생성
        if(list == null){
            list = new ArrayList<FileCommand>();
        }
        //세션이 있다면 list가 존재 -> list에 어떤 상품이 있는지 확인
        for(int i=0; i<list.size(); i++){
            //만약 리스트가 존재하고 파일정보가 있다면 취소 진행
            if(list.get(i).getStoreFile().equals(fileCommand.getStoreFile())){
                list.remove(i);
                newFile = false; //새로운 파일x -> 리스트에 추가되지 않도록 false처리
                num = "0";
                break;
            }
        }
        //삭제할 파일이 새로 선택된 경우
        if(newFile){
            list.add(fileCommand);
            num = "1";
        }
        //세션에 변경된 내용 저장
        session.setAttribute("fileList", list);
        //num -> ajax에 전달.
        //삭제취소: 0, 삭제:1 로 전달됨
        return num;
    }
}
