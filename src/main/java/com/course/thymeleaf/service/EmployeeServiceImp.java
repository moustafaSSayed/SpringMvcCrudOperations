package com.course.thymeleaf.service;

import com.course.thymeleaf.JPA.EmployeeJPA;
import com.course.thymeleaf.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeJPA employeeJPA;

    public EmployeeServiceImp(EmployeeJPA employeeJPA) {
        this.employeeJPA = employeeJPA;
    }

    @Override
    public Employee retrieve(int Id) {
        return employeeJPA.retrieve(Id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeJPA.findAll();
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeJPA.findByLastName(lastName);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeJPA.save(employee);
    }

    @Override
    @Transactional
    public void delete(int Id) {
        employeeJPA.delete(Id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        employeeJPA.deleteAll();
    }
}
