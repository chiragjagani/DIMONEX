package com.stpl.dimonex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	public Department(String name, double diamondPrice) {
		super();
		this.name = name;
		this.diamondPrice = diamondPrice;
	}

	private String name;
	private double diamondPrice;

	// Default constructor
	public Department() {
	}

	// Parameterized constructor
	public Department(int id, String name, double diamondPrice) {
		this.id = id;
		this.name = name;
		this.diamondPrice = diamondPrice;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiamondPrice() {
		return diamondPrice;
	}

	public void setDiamondPrice(double diamondPrice) {
		this.diamondPrice = diamondPrice;
	}

	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", name='" + name + '\'' + ", diamondPrice=" + diamondPrice + '}';
	}
}