package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.DepartmentService;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "index";
    }

    @GetMapping("/showDepartments")
    public String viewDepartmentPage(Model model){
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "department";
    }

    @GetMapping("/showNewEmployeeForm")
    public String viewNewEmployeePage(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "new_employee";
    }

    @GetMapping("/showNewDepartment")
    public String viewNewDepartmentPage( Model model){
        Department department = new Department();
        model.addAttribute("department", department);
        return "new_department";
    }

    @PostMapping("/saveEmployee")
    // koresponduje s <form action="#" th:action="@{/saveEmployee}" th:object="${employee}" v subore new_employee.html
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @PostMapping("/saveDepartment")
    public String saveEmployee(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/showDepartments";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable long id, Model model) {
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("listDepartments", departmentService.getAllDepartments());
        return "update_employee";
    }

    @GetMapping("/showFormForUpdateDepartment/{id}")
    public String showFormForUpdateDepartment(@PathVariable long id, Model model) {
        // get employee from the service
        Department department = departmentService.getDepartmentById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("department", department);
        return "update_department";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value="id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable (value="id") long id) {
        this.departmentService.deleteDepartmentById(id);
        return "redirect:/showDepartments";
    }

}
