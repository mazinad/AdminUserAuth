package com.userAuthentication.main.controller;

import java.util.List;

import com.userAuthentication.main.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@Autowired
	private DepartmentService departmentService;
	Logger log= LoggerFactory.getLogger(this.getClass());
	@GetMapping("/s_index")
	public String viewHomePage(Model model) {
		log.info("Just Entered the Student Page");
		model.addAttribute("listStudent", studentService.getStudents());
		return "s_index";
	}

	@GetMapping("/showNewStudent")
	public String showNewStudent(Model model) {
		// create model attribute to bind form data
		try {
			Student student = new Student();
			model.addAttribute("student", student);
			model.addAttribute("listDepartments",departmentService.getAllDepartments());
		}catch(Exception ex){
			log.error("An error just occured while displaying the page"+ex);
		}
		return "new_student";

	}
	@GetMapping("/addStudentCourses")
	public String showStudentCourses(Model model){
		try {
			Student student1 = new Student();
			model.addAttribute("student", student1);
			model.addAttribute("listAllCourses", courseService.getAllCourses());
			model.addAttribute("listStudent", studentService.getStudents());
		}catch(Exception ex){
			log.error("An error just occured while displaying the Assigned Courses"+ex);
		}
		return "Student_Course";
	}
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		// save Student to database
		try {
			studentService.saveStudents(student);
		}catch(Exception ex){
			log.error("An Error Occured while saving the student"+ex);
		}

		return "redirect:/s_index";

	}
	@PostMapping("/saveStudent1")
	public String saveStudent1(@ModelAttribute("student") Student student) {
		// save employee to database
		studentService.saveStudents(student);
		return "redirect:/Student_Course"; 

	}

	@GetMapping("/showFormForUpdateStudent/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get Student from the service
		try {
			Student student = studentService.getStudentById(id);
			// set Student as model attribute to pre populate the form
			model.addAttribute("student", student);
		}catch(Exception ex){
		log.error("An error occured while fetching the Id"+id);
		}
		return "update_Student";
	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete Student method
		try {
			this.studentService.deleteStudentId(id);
		}catch(Exception ex){
			log.error("Failed to delete this student with this id: "+id);
		}

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
