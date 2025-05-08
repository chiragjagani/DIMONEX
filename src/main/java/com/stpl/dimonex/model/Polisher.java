package com.stpl.dimonex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "polishers")
public class Polisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String skillLevel;
	private String experienceLevel;
	private Boolean availableStatus;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "polisher")
	private List<Task> tasksAssigned;

	public int getDepartment_id() {
		return department_id;
	}

	public List<Task> getTasksAssigned() {
		return tasksAssigned;
	}

	public void setTasksAssigned(List<Task> tasksAssigned) {
		this.tasksAssigned = tasksAssigned;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;

	int department_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public Boolean getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(Boolean availableStatus) {
		this.availableStatus = availableStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Polisher() {
		super();
	}

	public Polisher(Long id, String skillLevel, String experienceLevel, Boolean availableStatus, User user,
			Manager manager) {
		super();
		this.id = id;
		this.skillLevel = skillLevel;
		this.experienceLevel = experienceLevel;
		this.availableStatus = availableStatus;
		this.user = user;
		this.manager = manager;
	}

	public Polisher(Long id, String skillLevel, String experienceLevel, Boolean availableStatus, User user,
			List<Task> tasksAssigned, Manager manager, int department_id) {
		super();
		this.id = id;
		this.skillLevel = skillLevel;
		this.experienceLevel = experienceLevel;
		this.availableStatus = availableStatus;
		this.user = user;
		this.tasksAssigned = tasksAssigned;
		this.manager = manager;
		this.department_id = department_id;
	}

	@Override
	public String toString() {
		return "Polisher [id=" + id + ", skillLevel=" + skillLevel + ", experienceLevel=" + experienceLevel
				+ ", availableStatus=" + availableStatus;
	}
}
