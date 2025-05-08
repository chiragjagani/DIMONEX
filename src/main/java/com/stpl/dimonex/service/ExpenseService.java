package com.stpl.dimonex.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dimonex.dao.ExpenseDAO;
import com.stpl.dimonex.model.Expense;
import com.stpl.dimonex.model.ManagerSalary;
import com.stpl.dimonex.model.MonthlyFinancialSummary;
import com.stpl.dimonex.model.Salary;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseDAO expenseDAO;

	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ManagerSalaryService managerSalaryService;

	public void calculateAndSaveMonthlyExpenses() {
		double equipment = 100;
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		int year = today.getYear();
		
		boolean alreadyExist=expenseDAO.existsByMonthAndYear(month, year);
		if(alreadyExist) {
			return;
		}
		List<Salary> salaries = salaryService.getSalariesByMonthYear(month, year);
		double totalSalary = salaries.stream().mapToDouble(s -> s.getTotalSalary()).sum();

		List<ManagerSalary> managerSalary = managerSalaryService.getSalariesByMonthYear(month, year);
		//System.out.print("PRITING FROM EXPENSE SERVICE..."+managerSalary.get(0).getAmount());
		totalSalary = totalSalary + managerSalary.stream().mapToDouble(s -> s.getAmount()).sum();
		

		Expense expense = new Expense(totalSalary, equipment, month, year);
		
		expenseDAO.saveExpense(expense);
	}

	public List<Expense> getAllExpenses() {
		return expenseDAO.getAllExpenses();
	}

	public Expense getExpenseByMonthYear(int month, int year) {
		return expenseDAO.getExpenseByMonthYear(month, year);
	}
	public List<MonthlyFinancialSummary> getMonthlyProfitAndExpenseSummary() {
	    List<Expense> expenses = expenseDAO.getAllExpenses(); // adjust if you have filtering
	    List<MonthlyFinancialSummary> summaryList = new ArrayList<>();

	    for (Expense expense : expenses) {
	        int month = expense.getMonth();
	        int year = expense.getYear();

	        // Get total order amount for this month/year
	        double orderAmount = orderService.getTotalOrderAmountByMonthAndYear(month, year);

	        // Calculate total expense and profit
	        double totalExpense = expense.getSalaryExpense() + expense.getEquipmentExpense();
	        System.out.print("TOTAL EXPENSE IS..."+totalExpense);
	        double profit = orderAmount - totalExpense;

	        summaryList.add(new MonthlyFinancialSummary(month, year, totalExpense, profit));
	    }

	    return summaryList;
	}

}
