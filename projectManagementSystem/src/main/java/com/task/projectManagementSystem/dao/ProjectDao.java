package com.task.projectManagementSystem.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import com.task.projectManagementSystem.db.Project;
import io.dropwizard.hibernate.AbstractDAO;

public class ProjectDao extends AbstractDAO<Project>{

	public ProjectDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Project createProject(Project project) {
        return persist(project);
	}
	
	public List<Project> findAllProjects() {
		return list(namedTypedQuery("com.task.projectManagementSystem.db.Project.findAll"));
	} 
	
	public Project findById(Long id) {
        return get(id);
    }
	
	public List<Project> findByProjectName(String projectName) {
		return list(namedTypedQuery("com.task.projectManagementSystem.db.Project.findByProjectName")
				.setParameter("projectName", projectName));
	}

}
