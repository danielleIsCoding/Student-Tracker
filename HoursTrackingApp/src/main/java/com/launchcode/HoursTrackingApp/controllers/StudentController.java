package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.domain.Subject;
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

    //Displays all students in database
    @RequestMapping("student/index")
    public String displayAllStudents(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User newUser = (authenticationController.getUserFromSession(session));
        Integer newUserId = newUser.getId();
        model.addAttribute("title", "All Students");
        model.addAttribute("user", studentRepository.findById(newUserId));
        model.addAttribute("student", newUser.getStudent());
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
    public String displayAllSubjects(Model model, @PathVariable int studentId, Subject subject){
        model.addAttribute("subjects", subjectRepository.findById(studentId));
        model.addAttribute("subjects", subject.getTotalHours());
        model.addAttribute("student", studentRepository.findById(studentId));
        return "student/view/{studentId}";
    }

    @PostMapping("student/view/{studentId}")
    public String deleteStudent( @PathVariable int studentId){
        studentRepository.deleteById(studentId); ;
        return "redirect:/student/index";
    }

    @RequestMapping("student/view/{studentId}/editStudent/{studentId}")
        public String editSubjectForm(@PathVariable int studentId, @PathVariable int subjectId, Model model ) {
        model.addAttribute("student", studentRepository.findById(studentId));
        model.addAttribute("subject", subjectRepository.findById(subjectId));
        return "student/editStudent";
    }

    @PostMapping("student/view/{studentId}/editStudent/{studentId}")
        public String editStudent(@PathVariable int studentId, @ModelAttribute @Valid Student student, Model model){
        Student studentDB = studentRepository.findById(studentId).orElse(null);
        studentDB.setName(student.getName());
        studentDB = studentRepository.save(studentDB);
        return "redirect:/student/view/{studentId}";
    }
}








