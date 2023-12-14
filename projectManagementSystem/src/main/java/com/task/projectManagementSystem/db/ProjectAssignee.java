package com.task.projectManagementSystem.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "projectAssignee")
@NamedQueries({
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.findByEmployeeId",
            query = "select pa from ProjectAssignee pa where pa.employeeId like :employeeId"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.findByProjectId",
    query = "select pa from ProjectAssignee pa where pa.projectId like :projectId"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.deleteByProjectIdAndEmployeeId",
    query = "delete from ProjectAssignee pa WHERE pa.projectId like :projectId AND pa.employeeId = :employeeId"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.findAll",
    query = "select pa from ProjectAssignee pa"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.findByProjectIdAndEmployeeId",
    query = "select pa from ProjectAssignee pa where pa.projectId like :projectId AND pa.employeeId = :employeeId"),
	@NamedQuery(
	        name = "com.task.projectManagementSystem.db.ProjectAssignee.findByEmployeeIdWithEmployee",
	        query = "SELECT new com.task.projectManagementSystem.dto.ProjectAssignDTO(" +
	                "pa.projectId, pa.employeeId, p.projectName, p.projectDescription, " +
	                "e.firstName, e.lastName, e.department) " +
	                "FROM ProjectAssignee pa INNER JOIN Project p on pa.projectId = p.id inner join Employee e on pa.employeeId = e.id " +
	                "WHERE pa.employeeId = :employeeId"),
	@NamedQuery(name = "com.task.projectManagementSystem.db.ProjectAssignee.findAllWithEmployee",
	query = "SELECT new com.task.projectManagementSystem.dto.ProjectAssignDTO(" +
            "pa.projectId, pa.employeeId, p.projectName, p.projectDescription, " +
            "e.firstName, e.lastName, e.department) " +
            "FROM ProjectAssignee pa INNER JOIN Project p on pa.projectId = p.id inner join Employee e on pa.employeeId = e.id"),
	@NamedQuery(
	        name = "com.task.projectManagementSystem.db.ProjectAssignee.findByProjectIdWithEmployee",
	        query = "SELECT new com.task.projectManagementSystem.dto.ProjectAssignDTO(" +
	                "pa.projectId, pa.employeeId, p.projectName, p.projectDescription, " +
	                "e.firstName, e.lastName, e.department) " +
	                "FROM ProjectAssignee pa INNER JOIN Project p on pa.projectId = p.id inner join Employee e on pa.employeeId = e.id " +
	                "WHERE pa.projectId = :projectId")
})
public class ProjectAssignee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long projectId;
	private Long employeeId;
	
	public ProjectAssignee() {
		super();
	}

	public ProjectAssignee(Long id, Long projectId, String projectName, String projectDescription, Long employeeId) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.employeeId = employeeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "ProjectAssignee [id=" + id + ", projectId=" + projectId + ", employeeId=" + employeeId + "]";
	}



	
	
}
