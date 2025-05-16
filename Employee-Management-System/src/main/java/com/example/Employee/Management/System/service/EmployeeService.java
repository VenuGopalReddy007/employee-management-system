package com.example.Employee.Management.System.service;

import com.example.Employee.Management.System.model.Employee;
import com.example.Employee.Management.System.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    public Employee createEmployee(Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }


}
