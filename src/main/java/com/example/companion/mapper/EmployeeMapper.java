package com.example.companion.mapper;

import com.example.companion.domain.EmployeeDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    public String autoNum();
    public Integer employeeInsert(EmployeeDTO dto);
    public List<EmployeeDTO> employeeAllSelect(StartEndPageDTO sepDTO);
    public int employeeCount(String searchWord);
    public Integer employeeDelete(@Param("employeesNum")String empsDel[]);
}
