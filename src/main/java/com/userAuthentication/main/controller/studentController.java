package com.userAuthentication.main.controller;

import java.util.ArrayList;
import java.util.List;

import com.userAuthentication.main.model.Course;
import com.userAuthentication.main.service.*;
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
	@Autowired
	private AssignCourses assignCourses;
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
	@GetMapping("/addStudentCourses/{id}")
	public String showStudentCourses(@PathVariable(value = "id") long id, Model model){
		try {
			Student std=studentService.getStudentById(id);
			List<Course> cr=courseService.getAllCourses();
			model.addAttribute("course",cr);
			model.addAttribute("student",std);
		}catch(Exception ex){
			log.error("An error just occured while displaying the Assigned Courses"+ex);
		}
		return "Student_Course";
	}
	@PostMapping("/saveAssignedCourses")
	public String saveStudent1(@ModelAttribute("student") Student students) {
		// save employee to database
		System.out.println("student searched "+ students.getCourses().size());
		Student st1 = studentService.getStudentById(students.getSt_id());
		List<Course> selectedCOurses = students.getCourses();
		List<Course> existingCOurses = new ArrayList<>();
		existingCOurses = st1.getCourses();
		List<Course> allCourses = new ArrayList<>();
		allCourses.addAll(existingCOurses);
		allCourses.addAll(selectedCOurses);
		System.out.println("all courses "+allCourses.size());
		students.setCourses(allCourses);
		try {
			assignCourses.saveAssignedCourses(students);
		}catch (Exception ex){
			ex.printStackTrace();
			log.error("An Error Occured while saving the Assigned Courses"+ex);
		}
		return "redirect:/s_index";

	}

	@GetMapping("/showFormForUpdateStudent/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get Student from the service
		try {
			Student student = studentService.getStudentById(id);
			// set Student as model attribute to pre populate the form
			model.addAttribute("student", student);
			model.addAttribute("listDepartments",departmentService.getAllDepartments());
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
	@GetMapping("/check-student-courses/{id}")
	public String checkSelectedCourses(@PathVariable(value = "id") Integer id, Model model) {
		//get Student from service
		Student student = studentService.getStudentById(id);
		// set Student as model attribute to pre-populate the form
		model.addAttribute("student", student);
		// get list of selected courses;
		List<Course>courses = student.getCourses();
		model.addAttribute("courses",courses);
		return "selectedCourses";

	}
}
