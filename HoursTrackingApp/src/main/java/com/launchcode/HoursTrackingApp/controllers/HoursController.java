package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Hours;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/view/{studentId}/subject/{subjectId}")
public class HoursController {

    @GetMapping("addHours")
    public String displayAddHoursForm(Model model) {
        model.addAttribute(new Hours());
        return "student/addHours";
    }
}
