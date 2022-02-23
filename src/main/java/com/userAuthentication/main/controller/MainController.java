package com.userAuthentication.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/test")
	public String editProfiletest() {
		return "edit-profile";
	}
	@GetMapping("/edit-profil")
	public String editProfile() {
		return "edit-profile";
	}
//	@GetMapping("/student")
//	public String s_index() {
//		return "c_index";
//	}
}
