package com.stpl.dimonex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.service.ManagerSalaryService;
import com.stpl.dimonex.service.ManagerService;

@Controller
public class ManagerSalaryController {

	@Autowired
	private ManagerSalaryService managerSalaryService;

	@Autowired
	private ManagerService managerService;

	// 🔹 Show salary form to add new salary
	@GetMapping("/managerSalary/add")
	public String showAddSalaryForm(Model model) {
		System.out.print("HELLO PRINING FROM SHOW ADD MANAGER SALARY....");
		List<Manager> managers = managerService.getAllManagers();
		model.addAttribute("managers", managers);
		return "manager-salary-form"; // Create this JSP for form input
	}

	// 🔹 Handle salary submission
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
			return "redirect:/adminDashboard";
		}

		managerSalaryService.processManagerSalary(manager, month, year, amount);
		return "redirect:/adminDashboard";
	}

}
