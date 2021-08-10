package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.domain.Subject;
import com.launchcode.HoursTrackingApp.repositories.StudentRepository;
import com.launchcode.HoursTrackingApp.repositories.SubjectRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("student/view/{studentId}")
public class SubjectController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("addSubjects")
    public String addForm() {
        return "subjects/addSubjects";
    }

    @GetMapping("addSubjects")
    public String displayAddSubjectForm(Model model) {
        model.addAttribute(new Subject());
        return "subjects/addSubjects";
    }

    @PostMapping("addSubjects")
    public String processAddSubjectForm(@ModelAttribute @Valid Subject newSubject, @PathVariable int studentId,
                                        Errors errors, Model model) {
        Optional<Student> newStudentId = studentRepository.findById(studentId);
        newSubject.setStudents((newStudentId.get()));
        newSubject.setTotalHours(newSubject.getTotalHours());
        model.addAttribute(subjectRepository.save(newSubject));

        return "redirect:/student/view/{studentId} ";
    }

    @RequestMapping("subject/{subjectId}/editSubject")
    public String editSubjectForm(@PathVariable int studentId, @PathVariable int subjectId, Model model) {
        model.addAttribute("student", studentRepository.findById(studentId).orElse(null));
        model.addAttribute("subject", subjectRepository.findById(subjectId).orElse(null));
        return "subjects/editSubject";
    }

    @PostMapping("subject/{subjectId}/editSubject")
    public String editSubject(@PathVariable int subjectId, @ModelAttribute @Valid Subject updatedSubject, Model model) {
        Subject subjectDB = subjectRepository.findById(subjectId).orElse(null);
        subjectDB.setName(updatedSubject.getName());
        subjectDB.setTotalHours(updatedSubject.getTotalHours());
        subjectDB = subjectRepository.save(subjectDB);
        return "redirect:/student/view/{studentId}";
    }

    @PostMapping("subject/{subjectId}")

    public String deleteSubject(@PathVariable int subjectId) {

        @Valid Subject subject = subjectRepository.findById(subjectId).get();
        subjectRepository.deleteById(subjectId);
        subjectRepository.delete(subject);
        return "redirect:/student/view/{studentId}";
    }

    @GetMapping("subject/{subjectId}/export-subject")
    public void exportCSV(HttpServletResponse response, @PathVariable int subjectId) throws Exception {
        //set file name and content type
        String filename = "subjects.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<Subject> writer = new StatefulBeanToCsvBuilder<Subject>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all Students to csv file
        writer.write(subjectRepository.findById(subjectId).orElse(null));
    }
}



