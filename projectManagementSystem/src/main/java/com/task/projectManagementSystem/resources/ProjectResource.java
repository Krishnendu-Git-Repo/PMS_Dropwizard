package com.task.projectManagementSystem.resources;

import java.util.List;


import com.task.projectManagementSystem.dao.EmployeeDao;
import com.task.projectManagementSystem.dao.ProjectAssigneeDao;
import com.task.projectManagementSystem.dao.ProjectDao;
import com.task.projectManagementSystem.db.Employee;
import com.task.projectManagementSystem.db.Project;
import com.task.projectManagementSystem.db.ProjectAssignee;
import com.task.projectManagementSystem.dto.ProjectAssignDTO;
import com.task.projectManagementSystem.exception.CustomException;
import com.task.projectManagementSystem.exception.CustomResponse;
import com.task.projectManagementSystem.mail.MailSender;
import com.task.projectManagementSystem.mail.SmtpConfiguration;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

	private final ProjectDao projectDao;
	private final ProjectAssigneeDao projectAssigneeDao;
	private final MailSender mailSender;
	private final EmployeeDao employeeDao;

	public ProjectResource(ProjectDao projectDao, ProjectAssigneeDao projectAssigneeDao,
			SmtpConfiguration smtpConfiguration, EmployeeDao employeeDao) {
		this.projectDao = projectDao;
		this.projectAssigneeDao = projectAssigneeDao;
		this.mailSender = new MailSender(smtpConfiguration.getHost(), smtpConfiguration.getPort(),
				smtpConfiguration.getUsername(), smtpConfiguration.getPassword(), smtpConfiguration.isTls());
		this.employeeDao = employeeDao;
	}
	
	/* This API is responsible for create a Project in project table */
	@POST
	@Path("/addProject")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(@Valid Project project) {
		try {
			List<Project> findByProjectName = projectDao.findByProjectName(project.getProjectName());
			if(findByProjectName.size() > 0) {
				throw new CustomException("This Project is already present in Database");	
			} else {
				projectDao.createProject(project);
				return Response.status(Status.CREATED).build();
			}		
		} catch (CustomException e) {
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomResponse(e.getMessage())).build();
		} catch (Exception e1) {
			throw new CustomException(e1.getMessage());
		}
	}
	
	/* This API is responsible for fetching all Project details */
	@GET
	@Path("/allProjects")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> findAllProjects() {
		try {
			return projectDao.findAllProjects();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}	
	}
	
	/* This API is responsible for fetching Project details by projectId*/
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Project findById(@QueryParam("id") Long id) {
		try {
			return projectDao.findById(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/*
	 * This API is responsible for assigning a Project to employee and after
	 * assigning the project employee will get an email
	 */
	@POST
	@Path("/addAssignProject")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAssignProject(@Valid ProjectAssignee projectAssignee) {
		try {
			List<ProjectAssignee> findByProjectIdAndEmployeeId = projectAssigneeDao
					.findByProjectIdAndEmployeeId(projectAssignee.getProjectId(), projectAssignee.getEmployeeId());
			Project project = projectDao.findById(projectAssignee.getProjectId());
			if(findByProjectIdAndEmployeeId.size() > 0) {
				throw new CustomException("This Project is already assigned to him");	
			} else {
				projectAssigneeDao.addAssignProject(projectAssignee);
				Employee employee = employeeDao.findById(projectAssignee.getEmployeeId());
				String employeeEmail = employee.getEmail();
//				String mail = "krishnendusamanta761@gmail.com";
				String subject = "New Project is Assigned";
		        String body = "You have been assigned to a new project and the project name is: "+ project
		        		.getProjectName();
		        mailSender.sendMail(employeeEmail, subject, body);
				return Response.status(Status.CREATED).build();	
			}		
		} catch (CustomException e) {
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomResponse(e.getMessage())).build();
		} catch (Exception e1) {
			throw new CustomException(e1.getMessage());
		}
	}
	
	/*
	 * This API is responsible for fetching all assigned Project details with the
	 * employee details
	 */
	@GET
	@Path("/allAssignedProjects")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ADMIN")   @Auth User user
	public List<ProjectAssignDTO> findAllAssignedProjects() {
		try {
			return projectAssigneeDao.findAllAssignedProjectsToAllEmployees();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}	
	}
	
	/*
	 * This API is responsible for fetching assigned Project details with the
	 * employee details by a particular employeeId
	 */
	@GET
	@Path("/getAssignProjectByEmployeeId")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectAssignDTO> findProjectByEmployeeId(@QueryParam("employeeId") Long employeeId) {
		try {
			return projectAssigneeDao.findProjectsByEmployeeId(employeeId);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/*
	 * This API is responsible for fetching assigned Project details with the
	 * employee details by a particular projectId
	 */
	@GET
	@Path("/getAssignProjectByProjectId")
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectAssignDTO> findProjectByProjectId(@QueryParam("projectId") Long projectId) {
		try {
			return projectAssigneeDao.findProjectsByProjectId(projectId);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/*
	 * This API is responsible for deleting the assigned Project from the
	 * employee by a particular projectId and EmployeeId
	 */
	@DELETE
	@Path("/deleteAssignProjectByProjectIdAndEmployeeId")
	@UnitOfWork
	public Response deleteAssignProjectByProjectId(@QueryParam("projectId") Long projectId,
			@QueryParam("employeeId") Long employeeId) {
		try {
			Integer rowDeleted = projectAssigneeDao.deleteAssignProjectByProjectId(projectId, employeeId);
			if (rowDeleted > 0) {
				return Response.status(Status.OK).build();
			} else {
				throw new CustomException("This Project is already deleted from the employee");
			}
		} catch (CustomException e) {
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomResponse(e.getMessage())).build();
		} catch (Exception e1) {
			throw new CustomException(e1.getMessage());
		}
	}
}
