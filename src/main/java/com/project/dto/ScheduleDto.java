package com.project.dto;

import lombok.Data;

@Data
public class ScheduleDto {
    private int schedule_idx;
    private String schedule_startdate;
    private String schedule_enddate;
    private String schedule_info;
    private String schedule_target;
    private String schedule_division;
    private String schedule_time;
    private String schedule_note;
    private int schedule_important;
    private String create_time;
    private String update_time;
    private String year;
    private String month;
    private String date;
}
