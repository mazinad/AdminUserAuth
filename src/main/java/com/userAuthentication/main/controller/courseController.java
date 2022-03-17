package com.userAuthentication.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.userAuthentication.main.model.Course;
import com.userAuthentication.main.service.courseService;

import java.time.Clock;
import java.util.Optional;


@Controller
public class courseController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private courseService courseService;

    @GetMapping("/c_index")
    public String viewHomePage(Model model) {
        log.info("User has entered the Main Course Page");
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "c_index";

    }

    @GetMapping("/showNewCourses")
    public String showNewCourses(Model model) {
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";

    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save employee to database
        try {
            courseService.saveCourses(course);
        } catch (Exception e) {
            log.error("An error has while saving" + e);

        }
        log.debug("Existing the Save Course Part");
        return "redirect:/c_index";

    }

//    @GetMapping("/showFormForUpdate/{id}")
//    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
//        try {
//            Course course = courseService.getCoursesById(id);
//            model.addAttribute("course", course);
//        } catch (Exception e) {
//            log.error("Error Occured while fetching data for updating" + e);
//        }
//        return "update_course";
//    }
    @RequestMapping("/showFormForUpdate/{id}")
    @ResponseBody
    public Course findStudentById(@PathVariable Integer id, Model model){
        Course course=courseService.getCoursesById(id);
        return course;
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        try {
            this.courseService.deleteCoursesById(id);
        } catch (Exception e) {
            log.error("Error Occured while deleting  data for course" + e);
        }

        return "redirect:/c_index";
    }
}
