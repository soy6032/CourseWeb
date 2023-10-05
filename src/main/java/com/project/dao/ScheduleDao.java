package com.project.dao;

import com.project.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleDao {
    public List<ScheduleDto> scheduleList(ScheduleDto scheduleDTO);
}
