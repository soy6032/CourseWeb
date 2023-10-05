package com.project.controller;

import com.project.dao.CourseDao;
import com.project.dao.StudentDao;
import com.project.dto.CourseDto;
import com.project.dto.LoginDto;
import com.project.dto.StudentDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CourseController {

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    @GetMapping("/courseRegist")
    public String courseRegist(HttpSession session, Model model, CourseDto courseDto){
        String idx = ((LoginDto)session.getAttribute("user")).getStudent_idx();
        StudentDto student = studentDao.studentInfo(idx);
        model.addAttribute("info", student);

        courseDto.setSubject_department_idx(student.getStudent_department_idx());
        courseDto.setSubject_grade(student.getStudent_grade());
        courseDto.setSubject_type(student.getStudent_grades());

        courseDto.setCourseregist_student_idx(idx);

        List<CourseDto> list = courseDao.courseRegistList(courseDto);
        model.addAttribute("lists", list);

        List<CourseDto> list2 = courseDao.courseDetail(courseDto);
        model.addAttribute("lists2", list2);

        List<CourseDto> list3 = courseDao.courseDetail2(courseDto);
        model.addAttribute("lists3", list3);
        return "courseRegist";
    }

    @PostMapping("/courseRegist")
    public String courseRegistAction(HttpSession session, Model model, CourseDto courseDto, HttpServletRequest request){

        String[] courseregist_subject_idx = request.getParameterValues("courseregist_subject_idx");

        try {
            for (String courseregistSubjectIdx : courseregist_subject_idx) {
                courseDto.setCourseregist_subject_idx(Integer.parseInt(courseregistSubjectIdx));
                courseDto.setCourseregist_student_idx(((LoginDto) session.getAttribute("user")).getStudent_idx());
                courseDao.courseRegist(courseDto);
            }
            return "redirect:/courseRegist";
        } catch (Exception e) {
            // TODO: handle exception
            log.error(String.valueOf(e));
            return "redirect:/courseRegist?error";
        }
    }

    @GetMapping("/myCourse")
    public String myCourse(HttpSession session, Model model, CourseDto courseDto){
        String idx = ((LoginDto)session.getAttribute("user")).getStudent_idx();
        StudentDto student = studentDao.studentInfo(idx);
        model.addAttribute("info", student);

        courseDto.setSubject_department_idx(student.getStudent_department_idx());
        courseDto.setSubject_grade(student.getStudent_grade());
        courseDto.setSubject_type(student.getStudent_grades());
        courseDto.setCourseregist_student_idx(idx);

        List<CourseDto> list = courseDao.myCourse(courseDto);
        model.addAttribute("lists", list);

        List<CourseDto> list2 = courseDao.courseDetail(courseDto);
        model.addAttribute("lists2", list2);

        List<CourseDto> list3 = courseDao.courseDetail2(courseDto);
        model.addAttribute("lists3", list3);

        return "myCourse";
    }

    @GetMapping("/courseDelete/{courseregist_idx}")
    public String myCourse(@PathVariable String courseregist_idx){
        courseDao.courseDelete(courseregist_idx);
        return "redirect:/myCourse";
    }

    @GetMapping("/schedule")
    public String schedule(HttpSession session, Model model, CourseDto courseDto){

        String idx = ((LoginDto)session.getAttribute("user")).getStudent_idx();
        StudentDto student = studentDao.studentInfo(idx);
        model.addAttribute("info", student);

        courseDto.setSubject_department_idx(student.getStudent_department_idx());
        courseDto.setSubject_grade(student.getStudent_grade());
        courseDto.setSubject_type(student.getStudent_grades());
        courseDto.setCourseregist_student_idx(idx);

        List<CourseDto> list = courseDao.courseSchedule(courseDto);
        model.addAttribute("lists", list);

        return "/schedule";
    }

    @ResponseBody
    @GetMapping("/scheduleData")
    public List<CourseDto> scheduleData(HttpSession session, Model model, CourseDto courseDto){
        String idx = ((LoginDto)session.getAttribute("user")).getStudent_idx();
        StudentDto student = studentDao.studentInfo(idx);
        model.addAttribute("info", student);

        courseDto.setSubject_department_idx(student.getStudent_department_idx());
        courseDto.setSubject_grade(student.getStudent_grade());
        courseDto.setSubject_type(student.getStudent_grades());
        courseDto.setCourseregist_student_idx(idx);

        return courseDao.courseSchedule(courseDto);
    }
}
