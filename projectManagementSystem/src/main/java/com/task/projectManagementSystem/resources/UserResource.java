package com.task.projectManagementSystem.resources;

import com.task.projectManagementSystem.dao.UserDao;
import com.task.projectManagementSystem.db.User;
import com.task.projectManagementSystem.exception.CustomException;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private final UserDao userDao;
	
	public UserResource(UserDao userDao) {
		this.userDao = userDao;
		
	}
	
	@POST
	@Path("/addUser")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		try {
			System.out.println("Working");
			userDao.createUser(user);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
