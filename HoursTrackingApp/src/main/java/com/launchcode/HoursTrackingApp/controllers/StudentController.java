package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.repositories.StudentRepository;
import com.launchcode.HoursTrackingApp.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller

public class StudentController  {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("student/index")
    public String displayAllStudents(Model model){
        model.addAttribute("title", "All Students");
        model.addAttribute("student", studentRepository.findAll());
        return "student/index";
    }

    @GetMapping("student/add")
    public String displayAddStudentForm(Model model) {
        model.addAttribute(new Student());
        return "student/add";
    }

    @PostMapping("student/add")
    public String processAddStudentForm(@ModelAttribute @Valid Student newStudent,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "student/add";
        }

        model.addAttribute(studentRepository.save(newStudent));
        return "redirect:/student/index";
    }

    @GetMapping("student/view/{studentId}")
    public String displayViewStudent(Model model, @PathVariable int studentId) {

        Optional optStudent = studentRepository.findById(studentId);
        if (optStudent.isPresent()) {
            Student student = (Student) optStudent.get();
            model.addAttribute("student", student);
            model.addAttribute("subject", student.getSubjects());
            return "student/view";
        } else {
            return "redirect:../";
        }
    }
    @RequestMapping("student/view/{studentId}")
    public String displayAllSubjects(Model model, @PathVariable int studentId){
        model.addAttribute("subjects", subjectRepository.findById(studentId));
        return "student/view/{studentId}";
    }

}








