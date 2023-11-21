package com.course.thymeleaf.JPA;

import com.course.thymeleaf.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeJPAImpl implements EmployeeJPA {

    private EntityManager entityManager;
    @Autowired
    public EmployeeJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Employee retrieve(int Id) {
        return entityManager.find(Employee.class,Id);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee where lastName = :data", Employee.class);
        theQuery.setParameter("data", lastName);
        return theQuery.getResultList();
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee e = entityManager.merge(theEmployee);
        return e;
    }

    @Override
    public void delete(int Id) {
        Employee employee = retrieve(Id);
        entityManager.remove(employee);
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("Delete FROM Employee").executeUpdate();
    }
}
