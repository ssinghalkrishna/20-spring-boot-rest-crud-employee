package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection, param entityManager automatically created by SpringBoot
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        //return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //find() has entity type and primary key

        //return employee
        return theEmployee;
    }
//we don't use @Transactional at DAO layer. It will be handled at Service layer
    @Override
    public Employee save(Employee theEmployee) {
        //save or update employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        //if id==0 then insert/save else update

        //return dbEmployee
        return dbEmployee; //it has updated id from database in case of insert
    }
    //we don't use @Transactional at DAO layer. It will be handled at Service layer
    @Override
    public void deleteById(int theId) {
        //find emp by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //remove emp
        entityManager.remove(theEmployee);
    }
}
