package com.project.dto;

import lombok.Data;

@Data
public class CourseDto {
    private int subject_idx;
    private String subject_professor_idx;
    private int subject_department_idx;
    private String subject_name;
    private String subject_state;
    private int subject_grades;
    private int subject_grade;
    private String subject_classroom;
    private String create_time;
    private String update_time;
    private int subject_type;
    private String professor_name;
    private int courseregist_idx;
    private String courseregist_student_idx;
    private int courseregist_subject_idx;
    private int subject_detail_idx;
    private int subject_detail_subject_idx;
    private String subject_detail_week;
    private int subject_detail_period;
    private String subject_detail_period_total;
    private int subject_detail_week_num;
}
