package com.employee.employee.controller;

import java.util.List;

import com.employee.employee.model.Employee;
import com.employee.employee.services.EmployeeServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeServices.getAllEmployees());
        return "index";
    }

    @GetMapping("/getAllEmployee")
    @ResponseBody
    public String getAllEmployee(Model model) throws JsonProcessingException{
        model.addAttribute("listEmployees", employeeServices.getAllEmployees());
        List<Employee> lst=employeeServices.getAllEmployees();
        ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    String arrayToJson = objectMapper.writeValueAsString(lst);
    System.out.println(arrayToJson);
        return arrayToJson;
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
            employeeServices.saveEmployee(employee);
            return "redirect:/";
    }

    @GetMapping("/showFormforUpdate/{id}")
    public String showFormforUpdate(@PathVariable(value = "id") long id,Model model){
            // get employee service 
            Employee employee=employeeServices.getEmployeeId(id);
            model.addAttribute("employee",employee);
            return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){
            // call delete employee
            this.employeeServices.deleteEmployeeId(id);
            return "redirect:/";
    }

}
