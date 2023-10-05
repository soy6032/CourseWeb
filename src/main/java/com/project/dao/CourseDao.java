package com.project.dao;

import com.project.dto.CourseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {
    public List<CourseDto> courseRegistList(CourseDto courseDto);
    public List<CourseDto> courseDetail(CourseDto courseDto);
    public List<CourseDto> courseDetail2(CourseDto courseDto);
    public void courseRegist(CourseDto courseDto);
    public List<CourseDto> myCourse(CourseDto courseDto);
    public void courseDelete(String courseregist_idx);
    public List<CourseDto> courseSchedule(CourseDto courseDto);
}
