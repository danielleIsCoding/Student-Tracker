package com.launchcode.HoursTrackingApp.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SubjectController {

    @RequestMapping("student/addSubjects")
    public String goAddSubject (){
        return "student/addSubjects";
    }

}


