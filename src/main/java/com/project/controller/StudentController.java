package com.project.controller;

import com.project.dao.StudentDao;
import com.project.dto.CourseDto;
import com.project.dto.LoginDto;
import com.project.dto.StudentDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentDao studentDao;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginAction(LoginDto login, HttpSession session, @RequestParam(defaultValue = "/") String redirectURL){

        LoginDto user = studentDao.login(login);

        if(user != null){
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(12000);
            return "redirect:"+redirectURL;
        }else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/id")
    public String id(){
        return "id";
    }

    @GetMapping("/pw")
    public String pw(){
        return "pw";
    }

    @GetMapping("/info")
    public String info(HttpSession session, Model model, CourseDto courseDto){

        String idx = ((LoginDto)session.getAttribute("user")).getStudent_idx();
        model.addAttribute("info", studentDao.studentInfo(idx));

        return "info";
    }

    @PostMapping("/info")
    public String infoAction(StudentDto studentDto){
        try{
            studentDao.infoUpdate(studentDto);
            return "redirect:/info";
        }catch (Exception e) {
            log.error(String.valueOf(e));
            return "redirect:/info?error";
        }
    }

    @GetMapping("/pwch")
    public String pwch(){
        return "pwch";
    }

    @ResponseBody
    @PostMapping ("/pwch")
    public int ajaxPost(@RequestParam Map<String, String> param, LoginDto data, HttpSession session) {

        data.setStudent_idx(((LoginDto) session.getAttribute("user")).getStudent_idx());
        data.setStudent_pw(param.get("pw"));

        List<LoginDto> cnt = studentDao.pwCnt(data);
        if (!cnt.isEmpty()) {
            data.setStudent_pw(param.get("pw1"));
            try {
                studentDao.pwUpdate(data);
                return 0; // 수정완료
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
                return 2; // 수정오류
            }

        } else {
            return 1;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
