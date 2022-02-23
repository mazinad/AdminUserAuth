package com.userAuthentication.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userAuthentication.main.model.Course;
import com.userAuthentication.main.model.User;
import com.userAuthentication.main.service.UserService;
import com.userAuthentication.main.webData.UserRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	@GetMapping("/showFormForEdit/{id}")
	public String showFormForEdit(@PathVariable(value = "id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("profile", user);
		return "edit-profile";
	}
}
