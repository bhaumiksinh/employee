package com.employee.employee.services;

import java.util.List;
import java.util.Optional;


import com.employee.employee.model.Employee;
import com.employee.employee.repo.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeServices{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
      
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        // TODO Auto-generated method stub
       this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeId(long id) {
        // TODO Auto-generated method stub
        Optional<Employee> optional=employeeRepository.findById(id);
        Employee employee=null;
        if(optional.isPresent()){
            employee=optional.get();
        }else{
            throw new RuntimeException("Empoyee not found "+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeId(long id) {
        // TODO Auto-generated method stub
        this.employeeRepository.deleteById(id);
        
    }
    
}
