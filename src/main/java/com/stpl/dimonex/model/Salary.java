//package com.stpl.dimonex.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "salary")
//public class Salary {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private Long polisherId;
//    private int departmentId;
//    private Long diamondsCompleted;
//    private double totalSalary;
//
//    public Salary() {}
//
//    public Salary(Long polisherId, int departmentId, Long diamondsCompleted, double totalSalary) {
//        this.polisherId = polisherId;
//        this.departmentId = departmentId;
//        this.diamondsCompleted = diamondsCompleted;
//        this.totalSalary = totalSalary;
//    }
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public Long getPolisherId() { return polisherId; }
//    public void setPolisherId(Long polisherId) { this.polisherId = polisherId; }
//
//    public int getDepartmentId() { return departmentId; }
//    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
//
//    public Long getDiamondsCompleted() { return diamondsCompleted; }
//    public void setDiamondsCompleted(Long diamondsCompleted) { this.diamondsCompleted = diamondsCompleted; }
//
//    public double getTotalSalary() { return totalSalary; }
//    public void setTotalSalary(double totalSalary) { this.totalSalary = totalSalary; }
//
//	@Override
//	public String toString() {
//		return "Salary [id=" + id + ", polisherId=" + polisherId + ", departmentId=" + departmentId
//				+ ", diamondsCompleted=" + diamondsCompleted + ", totalSalary=" + totalSalary + "]";
//	}
//    
//}

package com.stpl.dimonex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary")
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Long polisherId; // The Polisher to whom the salary belongs
	private int departmentId; // The department associated with the polisher
	private Long diamondsCompleted; // Number of diamonds completed by the polisher
	private double totalSalary; // The total salary of the polisher
	private int salaryMonth; // The month the salary was paid
	private int salaryYear; // The year the salary was paid
	private boolean isPaid; // To mark if the salary has been paid (false if pending, true if paid)

	public Salary() {
	}

	public Salary(Long polisherId, int departmentId, Long diamondsCompleted, double totalSalary, int salaryMonth,
			int salaryYear, boolean isPaid) {
		this.polisherId = polisherId;
		this.departmentId = departmentId;
		this.diamondsCompleted = diamondsCompleted;
		this.totalSalary = totalSalary;
		this.salaryMonth = salaryMonth;
		this.salaryYear = salaryYear;
		this.isPaid = isPaid;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getPolisherId() {
		return polisherId;
	}

	public void setPolisherId(Long polisherId) {
		this.polisherId = polisherId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Long getDiamondsCompleted() {
		return diamondsCompleted;
	}

	public void setDiamondsCompleted(Long diamondsCompleted) {
		this.diamondsCompleted = diamondsCompleted;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public int getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(int salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public int getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(int salaryYear) {
		this.salaryYear = salaryYear;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean paid) {
		isPaid = paid;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", polisherId=" + polisherId + ", departmentId=" + departmentId
				+ ", diamondsCompleted=" + diamondsCompleted + ", totalSalary=" + totalSalary + ", salaryMonth="
				+ salaryMonth + ", salaryYear=" + salaryYear + ", isPaid=" + isPaid + "]";
	}
}
