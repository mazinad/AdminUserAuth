package com.userAuthentication.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.userAuthentication.main.model.Course;
import com.userAuthentication.main.service.courseService;


@Controller
public class courseController {
	@Autowired
	private courseService courseService;
	@GetMapping("/c_index")
	public String viewHomePage(Model model) {
		model.addAttribute("listCourses", courseService.getAllCourses());

		return "c_index";
	}

	@GetMapping("/showNewCourses")
	public String showNewCourses(Model model) {
		// create model attribute to bind form data
		Course course = new Course();
		model.addAttribute("course",course);
		return "new_course";

	}

	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course course) {
		// save employee to database
		courseService.saveCourses(course);
		return "redirect:/c_index";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		Course course = courseService.getCoursesById(id);
		// set employee as model attribute to pre populate the form
		model.addAttribute("course", course);
		return "update_course";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.courseService.deleteCoursesById(id);
		return "redirect:/c_index";
	}
}
