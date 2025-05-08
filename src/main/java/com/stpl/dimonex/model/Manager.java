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
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    @OneToMany(mappedBy = "manager")
    private List<Task> tasksAssigned;

   
    public Manager(Long id, User user, List<Task> tasksAssigned, int department, Double expenses) {
		super();
		this.id = id;
		this.user = user;
		this.tasksAssigned = tasksAssigned;
		this.department = department;
		this.expenses = expenses;
	}

	public List<Task> getTasksAssigned() {
		return tasksAssigned;
	}

	public void setTasksAssigned(List<Task> tasksAssigned) {
		this.tasksAssigned = tasksAssigned;
	}

	private int department;
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

    public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	private Double expenses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public Manager(Long id, User user, Double expenses) {
		super();
		this.id = id;
		this.user = user;
		this.expenses = expenses;
	}

	public Manager() {
		super();
	}

	@Override
	public String toString() {
		return "Manager [id=" + id +" expenses=" + expenses + "]";
	}
}
