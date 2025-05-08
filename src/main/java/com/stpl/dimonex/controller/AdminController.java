
package com.stpl.dimonex.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stpl.dimonex.model.Admin;
import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.Expense;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.ManagerSalary;
import com.stpl.dimonex.model.MonthlyFinancialSummary;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.service.AdminService;
import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.EquipmentService;
import com.stpl.dimonex.service.ExpenseService;
import com.stpl.dimonex.service.ManagerSalaryService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.TaskService;
import com.stpl.dimonex.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ManagerService mangerSerive;

	@Autowired
	private PolisherService polisherService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ManagerSalaryService managerSalaryService;

	@GetMapping("/dashboard")
	public String showDashboard(@RequestParam("userId") Long userId,
			@RequestParam(value = "attendanceDate", required = false) String attendanceDate, Model model) {

		// Fetch Admin details using userId
		System.out.println("--------------USER ID IS" + userId);
		Admin admin = adminService.getAdminByUserId(userId);

		List<Manager> managers = mangerSerive.getAllManagers();

		List<Polisher> polishers = polisherService.getAllPolishers();

		List<Department> departments = departmentService.getAllDepartments();

		List<Equipment> equipment = equipmentService.getAllEquipment();

		if (attendanceDate == null || attendanceDate.isEmpty()) {
			attendanceDate = LocalDate.now().toString();
		}

		model.addAttribute("currentDate", LocalDate.now().toString());
		model.addAttribute("attendanceDate", attendanceDate);

		// Pass admin details to the model
		model.addAttribute("admin", admin);

		model.addAttribute("userId", userId);
		model.addAttribute("managers", managers);

		model.addAttribute("polishers", polishers);

		model.addAttribute("equipment", equipment);

		model.addAttribute("departments", departments);

		return "adminDashboard";
	}

	@PostMapping("/assignTask")
	public String assignTask(@RequestParam Long managerId, @RequestParam String taskName,
			@RequestParam String description, @RequestParam int numberOfDiamonds, @RequestParam String deadline,
			@RequestParam String track, @RequestParam("userId") Long userId) { // Add userId parameter

		System.out.print("USER ID IS" + userId);
		taskService.assignTask(managerId, taskName, description, numberOfDiamonds, deadline, track);
		return "redirect:/admin/dashboard?userId=" + userId; // Append userId to redirect
	}

	@PostMapping("/addManager")
	public String addManager(@RequestParam String username, @RequestParam String email, @RequestParam String password,
			@RequestParam double expenses, @RequestParam Long userId, Model model) {

		managerService.createManager(username, password, email, expenses);

		model.addAttribute("message", "Manager added successfully!");
		return "redirect:/admin/dashboard?userId=" + userId;
	}

	@PostMapping("/deleteManager")
	public String deleteManager(@RequestParam("id") Long managerId, @RequestParam("userId") Long userId) {
		System.out.println("----------Manager Id" + managerId);

		List<Manager> managers = managerService.getAllManagers();
		Long idis = managerId;
		for (Manager manager : managers) {
			if (manager.getUser().getId() == managerId) {
				idis = manager.getId();
			}
		}

		managerService.deleteManager(idis);
		return "redirect:/admin/dashboard?userId=" + userId; // Redirect to the admin dashboard after deletion
	}

	@PostMapping("/deletePolisher")
	public String deletePolisher(@RequestParam("id") Long polisherId, @RequestParam("userId") Long userId) {
		System.out.println("---------- Polisher Id: " + polisherId);

		// Fetch all polishers
		List<Polisher> polishers = polisherService.getAllPolishers();
		Long idToDelete = polisherId;

		// Find the actual Polisher ID if stored inside another entity
		for (Polisher polisher : polishers) {
			if (polisher.getUser().getId().equals(polisherId)) {
				idToDelete = polisher.getId();
				break;
			}
		}

		// Delete the polisher
		polisherService.deletePolisher(idToDelete);

		// Redirect to the admin dashboard
		return "redirect:/admin/dashboard?userId=" + userId;
	}

	@GetMapping("/expenses")
	public String showCompanyExpenses(Model model) {
		List<Expense> expenses = expenseService.getAllExpenses();
		expenseService.calculateAndSaveMonthlyExpenses();
		System.out.print("----PRINTINING EXP---"+expenses.get(0).getSalaryExpense());
		model.addAttribute("expenses", expenses);
		return "admin-expense-summary";
	}

	// 🔹 Show salary form to add new salary
	@GetMapping("/add")
	public String showAddSalaryForm(Model model) {
		List<Manager> managers = managerService.getAllManagers();
		model.addAttribute("managers", managers);
	    model.addAttribute("currentYear", LocalDate.now().getYear()); // add this line
		return "manager-salary-form"; // Create this JSP for form input
	}

	@PostMapping("/add")
	public String addSalary(@RequestParam("managerId") Long managerId, @RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("amount") double amount, Model model) {
		Manager manager = managerService.getManagerByUserId(managerId);
		if (manager == null) {
			model.addAttribute("error", "Manager not found.");
			return "error";
		}

		// Prevent duplicate entry
		boolean alreadyPaid = managerSalaryService.isSalaryAlreadyPaid(managerId, month, year);
		if (alreadyPaid) {
			model.addAttribute("message", "Salary already processed for this month.");
			return "manager-salary-form";
		}

		managerSalaryService.processManagerSalary(manager, month, year, amount);
		return "manager-salary-form";
	}
	
	@GetMapping("/manager-salary-logs")
	public String viewAllManagerSalaries(Model model) {
	    List<ManagerSalary> salaryLogs = managerSalaryService.getAllSalaries();
	    model.addAttribute("salaryLogs", salaryLogs);
	    return "managerSalaryLogs"; // Name of the JSP page we'll create next
	}
	@GetMapping("/monthly-expense-graph")
	public String showMonthlyGraphPage(Model model) {
	    List<Expense> expenses = expenseService.getAllExpenses();
	    model.addAttribute("expenses", expenses);
	    return "monthly-expense-graph"; // JSP file
	}

	@GetMapping("/monthly-profit-expense-graph")
	public String showProfitExpenseGraph(Model model) {
	    List<MonthlyFinancialSummary> summaries = expenseService.getMonthlyProfitAndExpenseSummary();
	    model.addAttribute("summaries", summaries);
	    return "profit-expense-graph"; // JSP file name
	}



}