package com.example.companion.mapper;

import com.example.companion.domain.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    public String autoNum();
    public Integer employeeInsert(EmployeeDTO dto);
}
