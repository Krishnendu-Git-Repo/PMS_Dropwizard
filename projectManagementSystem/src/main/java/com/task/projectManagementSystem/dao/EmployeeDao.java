package com.task.projectManagementSystem.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import com.task.projectManagementSystem.db.Employee;

import io.dropwizard.hibernate.AbstractDAO;

public class EmployeeDao extends AbstractDAO<Employee> {

	public EmployeeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}	
	
	public Employee createEmployee(Employee employee) {
        return persist(employee);
	}
	
	public List<Employee> findAllEmployee() {
		return list(namedTypedQuery("com.task.projectManagementSystem.db.Employee.findAll"));
	} 
	
	public Employee findById(Long id) {
        return get(id);
    }
}
