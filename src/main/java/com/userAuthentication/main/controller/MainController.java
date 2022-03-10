package com.userAuthentication.main.controller;

import com.userAuthentication.main.model.User;
import com.userAuthentication.main.service.DepartmentService;
import com.userAuthentication.main.service.UserService;
import com.userAuthentication.main.service.courseService;
import com.userAuthentication.main.service.studentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
	@Autowired
	private studentService Studentservice;
	@Autowired
	private courseService courseService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	Logger logger= LoggerFactory.getLogger(this.getClass());
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("studentCount",Studentservice.getStudents().size());
		model.addAttribute("courseCount", courseService.getAllCourses().size());
		model.addAttribute("departmentCount",departmentService.getAllDepartments().size());
		return "index";
	}
	@GetMapping("/test")
	public String editProfiletest(Authentication auth, Model model)
	{
		String findUsername=auth.getName();
		User user=userService.findByEmail(findUsername);
		if(user==null){
			logger.error("An error occured while fetching data for the specific user");
		}
		model.addAttribute("userLogged",user);
		return "edit-profile";
	}

}
