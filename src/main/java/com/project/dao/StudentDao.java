package com.project.dao;

import com.project.dto.LoginDto;
import com.project.dto.StudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    public LoginDto login(LoginDto dto);
    public StudentDto studentInfo(String idx);
    public void infoUpdate(StudentDto dto);
    public List<LoginDto> pwCnt(LoginDto data);
    public void pwUpdate(LoginDto data);
}
