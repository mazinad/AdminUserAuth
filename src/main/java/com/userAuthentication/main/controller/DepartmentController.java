package com.userAuthentication.main.controller;

import com.userAuthentication.main.model.Course;
import com.userAuthentication.main.model.Department;
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

@Controller
public class DepartmentController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/d_index")
    public String viewHomePage(Model model) {
        log.info("User has entered the Main Department Page");
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "d_index";

    }

    @GetMapping("/showNewDepartments")
    public String showNewCourses(Model model) {
        // create model attribute to bind form data
        Department department = new Department();
        model.addAttribute("department", department);
        return "new_department";

    }

    @PostMapping("/saveDepartment")
    public String saveCourse(@ModelAttribute("department") Department department) {
        // save department to database
        try {
            departmentService.saveDepartment(department);
        } catch (Exception e) {
            log.error("An error has while saving" + e);

        }
        log.debug("Exiting the Save Department Part");
        return "redirect:/d_index";

    }

    @GetMapping("/showFormForUpdateDepartment/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        try {
            Department department = departmentService.getDepartmentById(id);
            model.addAttribute("department", department);
        } catch (Exception e) {
            log.error("Error Occured while fetching data for updating" + e);
        }
        return "update_department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        try {
            this.departmentService.deleteDepartment(id);
        } catch (Exception e) {
            log.error("Error Occured while deleting  data for Department" + e);
        }

        return "redirect:/d_index";
    }
}
