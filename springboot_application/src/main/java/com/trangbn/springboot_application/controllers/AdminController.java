package com.trangbn.springboot_application.controllers;

import com.trangbn.springboot_application.model.Person;
import com.trangbn.springboot_application.model.SchoolClass;
import com.trangbn.springboot_application.repository.PersonRepository;
import com.trangbn.springboot_application.repository.SchoolClassRepository;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/displayStudents")
    public ModelAndView displayStudent(Model model, @RequestParam("classId") int classId, HttpSession session,
                                       @RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<SchoolClass> foundClass = schoolClassRepository.findById(classId);
        modelAndView.addObject("schoolClass", foundClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("schoolClass", foundClass.get());
        if(error != null){
            modelAndView.addObject("errorMessage", "Invalid email entered");
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute("person") Person person, Model model,  HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + schoolClass.getClassId() + "&error=true");
            return modelAndView;
        }
        personEntity.setSchoolClass(schoolClass);
        personRepository.save(personEntity);
        schoolClass.getPersons().add(personEntity);
        schoolClassRepository.save(schoolClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + schoolClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam("personId") int personId, HttpSession session) {
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        Optional<Person> foundPerson = personRepository.findById(personId);
        foundPerson.get().setSchoolClass(null);
        schoolClass.getPersons().remove(foundPerson.get());
        SchoolClass savedSchoolClass = schoolClassRepository.save(schoolClass);
        session.setAttribute("schoolClass", savedSchoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + schoolClass.getClassId());
        return modelAndView;
    }
}
