package com.stpl.dimonex.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {
	public Expense(Long expenseId, double salaryExpense, double equipmentExpense, int month, int year,
			LocalDate createdDate) {
		super();
		this.expenseId = expenseId;
		this.salaryExpense = salaryExpense;
		this.equipmentExpense = equipmentExpense;
		this.month = month;
		this.year = year;
		this.createdDate = createdDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseId;

	private double salaryExpense;
	private double equipmentExpense;

	private int month;
	private int year;

	private LocalDate createdDate;

	public Expense() {
		this.createdDate = LocalDate.now();
	}

	public Expense(double totalSalary, double equipment, int month2, int year2) {
		this.salaryExpense = totalSalary;
		equipmentExpense = equipment;
		this.month = month2;
		this.year = year2;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public double getSalaryExpense() {
		return salaryExpense;
	}

	public void setSalaryExpense(double salaryExpense) {
		this.salaryExpense = salaryExpense;
	}

	public double getEquipmentExpense() {
		return equipmentExpense;
	}

	public void setEquipmentExpense(double equipmentExpense) {
		this.equipmentExpense = equipmentExpense;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	

}
