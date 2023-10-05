package com.project.controller;

import com.project.dao.ScheduleDao;
import com.project.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ScheduleDao scheduleDao;

    @GetMapping("/")
    public String home(){
        return "redirect:/sched";
    }

    @GetMapping("/sched")
    public String sched(ScheduleDto data, Model model){

        LocalDate now = LocalDate.now();
        String dateValue = LocalDate.now().toString();
        data.setDate(dateValue);
        model.addAttribute("year", String.valueOf(now.getYear()));
        model.addAttribute("month", String.valueOf(now.getMonthValue()));

        List<ScheduleDto> scheduleList = scheduleDao.scheduleList(data);
        model.addAttribute("scheduleLists", scheduleList);

        return "index";
    }

    @PostMapping(value = "/sched")
    public String sched2(Model model, ScheduleDto data) {

        model.addAttribute("year", data.getYear());
        model.addAttribute("month", data.getMonth());

        data.setDate(data.getYear() + "-" + data.getMonth() + "-" + "01");

        List<ScheduleDto> scheduleList = scheduleDao.scheduleList(data);
        model.addAttribute("scheduleLists", scheduleList);

        return "index";
    }




}
