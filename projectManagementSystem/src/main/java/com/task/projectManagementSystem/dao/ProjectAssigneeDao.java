package com.task.projectManagementSystem.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.task.projectManagementSystem.db.ProjectAssignee;
import com.task.projectManagementSystem.dto.ProjectAssignDTO;

import io.dropwizard.hibernate.AbstractDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ProjectAssigneeDao extends AbstractDAO<ProjectAssignee> {

	public ProjectAssigneeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public ProjectAssignee addAssignProject(ProjectAssignee projectAssignee) {
        return persist(projectAssignee);
	}
	
	/* find assigned projects to all employee without the employee details */
	public List<ProjectAssignee> findAllAssignedProjects() {
		return list(namedTypedQuery("com.task.projectManagementSystem.db.ProjectAssignee.findAll"));
	}
	
	/* find assigned projects to all employee with the employee details */
	public List<ProjectAssignDTO> findAllAssignedProjectsToAllEmployees() {
	    EntityManager entityManager = currentSession().unwrap(EntityManager.class);
	    try {
	        TypedQuery<ProjectAssignDTO> query = entityManager.createNamedQuery(
	                "com.task.projectManagementSystem.db.ProjectAssignee.findAllWithEmployee",
	                ProjectAssignDTO.class
	        );
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	/* find assigned projects to a particular employee by EmployeeId without the employee details */
	public List<ProjectAssignee> findProjectByEmployeeId(Long employeeId){
		return list(namedTypedQuery("com.task.projectManagementSystem.db.ProjectAssignee.findByEmployeeId")
				.setParameter("employeeId", employeeId));
	}
	
	/* find assigned projects to a particular employee by EmployeeId with the employee details */
	public List<ProjectAssignDTO> findProjectsByEmployeeId(Long employeeId) {
	    EntityManager entityManager = currentSession().unwrap(EntityManager.class);
	    try {
	        TypedQuery<ProjectAssignDTO> query = entityManager.createNamedQuery(
	                "com.task.projectManagementSystem.db.ProjectAssignee.findByEmployeeIdWithEmployee",
	                ProjectAssignDTO.class
	        );
	        query.setParameter("employeeId", employeeId);
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	/* find assigned projects by projectId without the employee details */
	public List<ProjectAssignee> findProjectByProjectId(Long projectId){
		return list(namedTypedQuery("com.task.projectManagementSystem.db.ProjectAssignee.findByProjectId")
				.setParameter("projectId", projectId));
	}
	
	/* find assigned projects by projectId with the employee details */
	public List<ProjectAssignDTO> findProjectsByProjectId(Long projectId) {
	    EntityManager entityManager = currentSession().unwrap(EntityManager.class);
	    try {
	        TypedQuery<ProjectAssignDTO> query = entityManager.createNamedQuery(
	                "com.task.projectManagementSystem.db.ProjectAssignee.findByProjectIdWithEmployee",
	                ProjectAssignDTO.class
	        );
	        query.setParameter("projectId", projectId);
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	public Integer deleteAssignProjectByProjectId(Long projectId, Long employeeId) {
		Integer executeUpdate = namedQuery(
				"com.task.projectManagementSystem.db.ProjectAssignee.deleteByProjectIdAndEmployeeId")
				.setParameter("projectId", projectId).setParameter("employeeId", employeeId).executeUpdate();
		return executeUpdate;
	}

	public List<ProjectAssignee> findByProjectIdAndEmployeeId(Long projectId, Long employeeId) {
		return list(namedTypedQuery("com.task.projectManagementSystem.db.ProjectAssignee.findByProjectIdAndEmployeeId")
				.setParameter("projectId", projectId).setParameter("employeeId", employeeId));
	}
}
