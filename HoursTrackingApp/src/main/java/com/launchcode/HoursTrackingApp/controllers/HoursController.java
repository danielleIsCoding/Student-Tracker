package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Hours;
import com.launchcode.HoursTrackingApp.domain.Subject;
import com.launchcode.HoursTrackingApp.repositories.HoursRepository;
import com.launchcode.HoursTrackingApp.repositories.StudentRepository;
import com.launchcode.HoursTrackingApp.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("student/view/{studentId}/subject/{subjectId}")
public class HoursController {

    @Autowired
    private HoursRepository hoursRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public String displayAllHours(Model model, @PathVariable int subjectId, @PathVariable int studentId){

        Hours hours = hoursRepository.findById(subjectId).orElse(null);
        model.addAttribute("hours", hours);
        model.addAttribute("subject", subjectRepository.findById(subjectId).orElse(null));
        model.addAttribute("student", studentRepository.findById(studentId).orElse(null));
        return "hours/hoursView";
    }

    @GetMapping("addHours")
    public String displayAddHoursForm(Model model) {
        model.addAttribute(new Hours());
        return "hours/addHours";
    }

    @PostMapping("addHours")
    public String processAddHoursForm(@ModelAttribute @Valid Hours newHours,
                                        @PathVariable int subjectId, Model model) {
        Optional<Subject> newSubjectId = subjectRepository.findById(subjectId);
        newHours.setSubjects((newSubjectId.get()));
        model.addAttribute(hoursRepository.save(newHours));

        return "redirect:/student/view/{studentId}/subject/{subjectId}";
    }

    @GetMapping("editHours/{hoursId}")
    public String editHoursForm(@PathVariable int studentId, @PathVariable int subjectId, @PathVariable int hoursId, Model model ) {
        model.addAttribute("student", studentRepository.findById(studentId).orElse(null));
        model.addAttribute("subject", subjectRepository.findById(subjectId).orElse(null));
        model.addAttribute( "hours", hoursRepository.findById(hoursId).orElse(null));
        return "hours/editHours";
    }

    @PostMapping("editHours/{hoursId}")
    public String editHours(@PathVariable int hoursId, @ModelAttribute @Valid Hours updatedHours,  Model model){
        Hours hoursDB = hoursRepository.findById(hoursId).orElse(null);
        hoursDB.setDate(updatedHours.getDate());
        hoursDB.setTotal(updatedHours.getTotal());
        hoursDB.setNotes(updatedHours.getNotes());
        hoursDB = hoursRepository.save(hoursDB);
        return "redirect:/student/view/{studentId}/subject/{subjectId}";
    }

}
