package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.domain.User;
import com.launchcode.HoursTrackingApp.repositories.StudentRepository;
import com.launchcode.HoursTrackingApp.repositories.SubjectRepository;
import com.launchcode.HoursTrackingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller

public class StudentController  {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("student/index")
    public String displayAllStudents(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User newUser = (authenticationController.getUserFromSession(session));
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
    public String processAddStudentForm(@ModelAttribute @Valid Student newStudent, HttpServletRequest request,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "student/add";
        }
        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable(authenticationController.getUserFromSession(session));
        newStudent.setUser((user.get()));
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

    @PostMapping("student/view/{studentId}")
    public String deleteStudent( @PathVariable int studentId){
        studentRepository.deleteById(studentId); ;
        return "redirect:/student/index";
    }

    @GetMapping("student/view/{studentId}/editStudent/{studentId}")
        public String editStudentForm(@PathVariable int studentId, Model model ) {
        model.addAttribute("student", studentRepository.findById(studentId));
        return "student/editStudent";
    }

    @PostMapping("student/view/{studentId}/editStudent/{studentId}")
        public String editStudent(@PathVariable int studentId, @ModelAttribute @Valid Student student, Model model){


        Student studentDB = studentRepository.findById(studentId).orElse(null);

        System.out.println(student.getId());
        System.out.println(studentDB);
        System.out.println(student.getName());

        studentDB.setName(student.getName());
        studentDB = studentRepository.save(studentDB);
        return "redirect:/student/view/{studentId}";
    }

//    @PostMapping("student/view/{studentId}/editStudent/{studentId}")
//    public String processEditStudentForm(@ModelAttribute @Valid Student updatedStudent, @PathVariable int studentId,
//                                        Errors errors, Model model) {
//        Optional<Student> oldStudent = studentRepository.findById(studentId);
//        oldStudent.orElseThrow(null).setName(updatedStudent.getName());
//
//        System.out.println(oldStudent);
//        System.out.println(updatedStudent.getName());
//        return "redirect:/student/view/{studentId}";
//    }
}








