package com.launchcode.HoursTrackingApp.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HomeController {

    @GetMapping("/home")
    public String goHome (){
        return "home";
    }

}