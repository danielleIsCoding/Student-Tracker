package com.launchcode.HoursTrackingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    @RequestMapping
    public String showStudent(Model model){

        //This will return students for the logged in user
        return "students";
    }
}
