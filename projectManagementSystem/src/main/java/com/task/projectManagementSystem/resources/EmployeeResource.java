package com.task.projectManagementSystem.resources;


import java.util.List;

import com.task.projectManagementSystem.dao.EmployeeDao;
import com.task.projectManagementSystem.db.Employee;
import com.task.projectManagementSystem.exception.CustomException;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	
	private final EmployeeDao employeeDAO;
	
	public EmployeeResource(EmployeeDao employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@POST
	@Path("/addEmployee")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployee(@Valid Employee employee) {
		try {
			employeeDAO.createEmployee(employee);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
		
	@GET
	@Path("/allEmployee")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> findAllEmployee() {
		try {
			return employeeDAO.findAllEmployee();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}	
	}	 
	
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Employee findById(@QueryParam("id") Long id) {
		try {
			return employeeDAO.findById(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
}
