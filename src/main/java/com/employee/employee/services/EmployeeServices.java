package com.employee.employee.services;

import java.util.List;

import com.employee.employee.model.Employee;



public interface EmployeeServices {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);
    Employee getEmployeeId(long id);
    void deleteEmployeeId(long id); 
    
}
