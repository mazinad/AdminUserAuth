package com.userAuthentication.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userAuthentication.main.model.Student;
import com.userAuthentication.main.service.courseService;
import com.userAuthentication.main.service.studentImpl;
import com.userAuthentication.main.service.studentService;



@Controller
public class studentController {
	@Autowired
	private studentService studentService;
	@Autowired
	private courseService courseService;
	@Autowired
	private studentImpl studentImpl;
	@GetMapping("/s_index")
	public String viewHomePage(Model model) {
		model.addAttribute("listStudent", studentService.getStudents());
		return "s_index";
	}

	@GetMapping("/showNewStudent")
	public String showNewStudent(Model model) {
		// create model attribute to bind form data
		Student student = new Student();
		model.addAttribute("student",student);
		model.addAttribute("listAllCourses", courseService.getAllCourses());
		return "new_student";

	}
	@GetMapping("/addStudentCourses")
	public String showStudentCourses(Model model){
		Student student1 = new Student();
		model.addAttribute("student",student1);
		model.addAttribute("listAllCourses", courseService.getAllCourses());
		model.addAttribute("listStudent", studentService.getStudents());
		return "Student_Course";
	}
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		// save employee to database
		studentService.saveStudents(student);
		return "redirect:/s_index";

	}
	@PostMapping("/saveStudent1")
	public String saveStudent1(@ModelAttribute("student") Student student) {
		// save employee to database
		studentService.saveStudents(student);
		return "redirect:/s_index";

	}

	@GetMapping("/showFormForUpdateStudent/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		Student student = studentService.getStudentById(id);
		// set employee as model attribute to pre populate the form
		model.addAttribute("student", student);
		return "update_Student";
	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.studentService.deleteStudentId(id);
		return "redirect:/s_index";
	}
	@RequestMapping(path= { "/search"})	
	public String search(Student student ,Model model,String keyword) {
		try {
			if(keyword!=null) {
				List<Student>list=studentImpl.getByKeyWord(keyword);
				model.addAttribute("listStudent",list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/s_index";
	}
}
