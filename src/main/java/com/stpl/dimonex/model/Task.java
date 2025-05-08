package com.stpl.dimonex.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(nullable = false)
	private String taskName;

	@Column(nullable = false)
	private String description;

	@Column(name = "number_of_diamonds", nullable = false)
	private int numberOfDiamonds;

	@Column(nullable = false)
	private String deadline;

	@Column(nullable = false)
	private String track; // Current status of the task (e.g., "In Progress", "Completed")

	@Column(name = "extension_requested", nullable = false)
	private boolean extensionRequested=false;

	@Column(name = "requested_extension_deadline")
	private LocalDate requestedExtensionDeadline;

	@Column(name = "manager_response")
	private String managerResponse; // "Accepted", "Rejected" for extension

	@ManyToOne
	@JoinColumn(name = "polisher_id")
	private Polisher polisher; // Polisher assigned to the task

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager; // Manager overseeing the task

	 @ManyToOne
	    @JoinColumn(name = "department_id")
	    private Department department;
	 
	 
	 
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfDiamonds() {
		return numberOfDiamonds;
	}

	public void setNumberOfDiamonds(int numberOfDiamonds) {
		this.numberOfDiamonds = numberOfDiamonds;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public boolean isExtensionRequested() {
		return extensionRequested;
	}

	public void setExtensionRequested(boolean extensionRequested) {
		this.extensionRequested = extensionRequested;
	}

	public LocalDate getRequestedExtensionDeadline() {
		return requestedExtensionDeadline;
	}

	public void setRequestedExtensionDeadline(LocalDate requestedExtensionDeadline) {
		this.requestedExtensionDeadline = requestedExtensionDeadline;
	}

	public String getManagerResponse() {
		return managerResponse;
	}

	public void setManagerResponse(String managerResponse) {
		this.managerResponse = managerResponse;
	}

	public Polisher getPolisher() {
		return polisher;
	}

	public void setPolisher(Polisher polisher) {
		this.polisher = polisher;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Task(Long taskId, String taskName, String description, int numberOfDiamonds, String deadline,
			String track, boolean extensionRequested, LocalDate requestedExtensionDeadline, String managerResponse,
			Polisher polisher, Manager manager) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.numberOfDiamonds = numberOfDiamonds;
		this.deadline = deadline;
		this.track = track;
		this.extensionRequested = extensionRequested;
		this.requestedExtensionDeadline = requestedExtensionDeadline;
		this.managerResponse = managerResponse;
		this.polisher = polisher;
		this.manager = manager;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(String taskName, String description, int numberOfDiamonds, String deadline, String track,
			boolean extensionRequested, LocalDate requestedExtensionDeadline, String managerResponse, Polisher polisher,
			Manager manager) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.numberOfDiamonds = numberOfDiamonds;
		this.deadline = deadline;
		this.track = track;
		this.extensionRequested = extensionRequested;
		this.requestedExtensionDeadline = requestedExtensionDeadline;
		this.managerResponse = managerResponse;
		this.polisher = polisher;
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", description=" + description
				+ ", numberOfDiamonds=" + numberOfDiamonds + ", deadline=" + deadline + ", track=" + track
				+ ", extensionRequested=" + extensionRequested + ", requestedExtensionDeadline="
				+ requestedExtensionDeadline + ", managerResponse=" + managerResponse + ", polisher=" + polisher
				+ ", manager=" + manager + ", department=" + department + "]";
	}

//	@Override
//	public String toString() {
//		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", description=" + description
//				+ ", numberOfDiamonds=" + numberOfDiamonds + ", deadline=" + deadline + ", track=" + track
//				+ ", extensionRequested=" + extensionRequested + ", requestedExtensionDeadline="
//				+ requestedExtensionDeadline + ", managerResponse=" + managerResponse + "]";
//	}

	
	
	
	
	// Getters and setters...

}
