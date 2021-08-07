package com.launchcode.HoursTrackingApp.controllers;

import com.launchcode.HoursTrackingApp.domain.Hours;
import com.launchcode.HoursTrackingApp.domain.Subject;
import com.launchcode.HoursTrackingApp.repositories.HoursRepository;
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


    @GetMapping("addHours")
    public String displayAddHoursForm(Model model) {
        model.addAttribute(new Hours());
        return "student/addHours";
    }

    @PostMapping("addHours")
    public String processAddHoursForm(@ModelAttribute @Valid Hours newHours,
                                        @PathVariable int subjectId, Model model) {
        Optional<Subject> newSubjectId = subjectRepository.findById(subjectId);
        newHours.setSubjects((newSubjectId.get()));
        model.addAttribute(hoursRepository.save(newHours));

        return "redirect:/student/view/{studentId}/subject/{subjectId}";
    }


}
