package com.task.projectManagementSystem.dto;

public class ProjectAssignDTO {

	private Long projectId;
	private Long employeeId;
    private String projectName;
    private String projectDescription;
    private String firstName;
    private String lastName;
    private String department;
	
    public ProjectAssignDTO() {
		super();
	}

	public ProjectAssignDTO(Long projectId, Long employeeId, String projectName, String projectDescription,
			String firstName, String lastName, String department) {
		super();
		this.projectId = projectId;
		this.employeeId = employeeId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "ProjectAssignDTO [projectId=" + projectId + ", employeeId=" + employeeId + ", projectName="
				+ projectName + ", projectDescription=" + projectDescription + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", department=" + department + "]";
	}

	
}
