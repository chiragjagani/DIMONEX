package com.stpl.dimonex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dimonex.dao.ManagerSalaryDAO;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.ManagerSalary;

@Service
public class ManagerSalaryService {

	@Autowired
	private ManagerSalaryDAO managerSalaryDAO;

	public void saveSalary(ManagerSalary managerSalary) {
		managerSalary.setPaymentDate(LocalDate.now());
		managerSalaryDAO.saveManagerSalary(managerSalary);
	}

	public void updateSalary(ManagerSalary managerSalary) {
		managerSalaryDAO.updateManagerSalary(managerSalary);
	}

	// Get all manager salary records
	public List<ManagerSalary> getAllSalaries() {
		return managerSalaryDAO.getAllManagerSalaries();
	}

	// Get salary record by ID
	public ManagerSalary getSalaryById(Long id) {
		return managerSalaryDAO.getManagerSalaryById(id);
	}

	public List<ManagerSalary> getSalariesByMonthYear(int month, int year) {
		return managerSalaryDAO.getManagerSalariesByMonthYear(month, year);
	}

	// Get salary for a specific manager by month/year
	public ManagerSalary getSalaryForManagerByMonthYear(Long managerId, int month, int year) {
		return managerSalaryDAO.getSalaryForManagerByMonthYear(managerId, month, year);
	}

	// Utility method to check if already paid
	public boolean isSalaryAlreadyPaid(Long managerId, int month, int year) {
		ManagerSalary salary = getSalaryForManagerByMonthYear(managerId, month, year);
		return salary != null && salary.isPaid();
	}

	public void processManagerSalary(Manager manager, int month, int year, double amount) {
		ManagerSalary existingSalary = getSalaryForManagerByMonthYear(manager.getId(), month, year);
		if (existingSalary == null) {
			ManagerSalary newSalary = new ManagerSalary();
			newSalary.setManager(manager);
			newSalary.setAmount(amount);
			newSalary.setMonth(month);
			newSalary.setYear(year);
			newSalary.setPaid(true);
			newSalary.setPaymentDate(LocalDate.now());
			saveSalary(newSalary);
		}
	}

}
