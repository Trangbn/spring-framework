package com.trangbn.springboot_application.controllers;

import com.trangbn.springboot_application.model.Person;
import com.trangbn.springboot_application.model.SchoolClass;
import com.trangbn.springboot_application.repository.PersonRepository;
import com.trangbn.springboot_application.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClass> classes = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("schoolClass", new SchoolClass());
        modelAndView.addObject("schoolClasses", classes);
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(@ModelAttribute("schoolClass") SchoolClass schoolClass, Model model) {
        schoolClassRepository.save(schoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam("id") int id) {
        Optional<SchoolClass> foundClass = schoolClassRepository.findById(id);
        for(Person person : foundClass.get().getPersons()) {
            person.setSchoolClass(null);
            personRepository.save(person);
        }

        schoolClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudent")
    public ModelAndView displayStudent(Model model, @RequestParam("classId") int classId) {
        ModelAndView modelAndView = new ModelAndView("student.html");
        return modelAndView;
    }

}
