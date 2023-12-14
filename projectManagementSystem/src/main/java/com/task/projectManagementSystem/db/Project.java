package com.task.projectManagementSystem.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;



@Entity
@Table(name = "projects")
@NamedQueries({
	@NamedQuery(name = "com.task.projectManagementSystem.db.Project.findAll",
            query = "select p from Project p"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.Project.findByProjectName",
    query = "select p from Project p where p.projectName like :projectName")
})
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String projectName;
    private String projectDescription;
	
    public Project() {
		super();
	}

	public Project(Long id, String projectName, String projectDescription) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", projectDescription=" + projectDescription
				+ "]";
	}

	
}
