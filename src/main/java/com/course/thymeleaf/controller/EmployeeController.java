package com.course.thymeleaf.controller;

import com.course.thymeleaf.entity.Employee;
import com.course.thymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees (Model model) {

        //retrieving all the employees from db
        List<Employee> employeesList = employeeService.findAll();

        //passing the list of employees to the model
        model.addAttribute("employees", employeesList);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id,
                                    Model model) {

        model.addAttribute("employee",employeeService.retrieve(id));

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        employeeService.delete(id);
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }
}
