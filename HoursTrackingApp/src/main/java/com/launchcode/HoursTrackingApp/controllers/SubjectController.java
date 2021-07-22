package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.domain.Subject;
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

public class SubjectController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    @RequestMapping("student/view/{studentId}/addSubjects")

    public String addForm (){
        return "student/addSubjects";
    }

    @GetMapping("student/view/{studentId}/addSubjects")
    public String displayAddSubjectForm(Model model) {
        model.addAttribute(new Subject());
        return "student/addSubjects";
    }

    @PostMapping("student/view/{studentId}/addSubjects")
    public String processAddSubjectForm(@ModelAttribute @Valid Subject newSubject, @PathVariable int studentId,
                                        Errors errors, Model model) {

        Optional<Student> newStudentId = studentRepository.findById(studentId);
        newSubject.setStudent((newStudentId.get()));
        model.addAttribute(subjectRepository.save(newSubject));

        return "redirect:/student/view/{studentId} ";
    }




}


