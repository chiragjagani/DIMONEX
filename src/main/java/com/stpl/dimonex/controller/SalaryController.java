package com.stpl.dimonex.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.SalaryService;
import com.stpl.dimonex.service.TaskService;
import com.stpl.dimonex.service.UserService;

@Controller
public class SalaryController {
	@Autowired
	private TaskService taskService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private PolisherService polisherService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@GetMapping("/create/{polisherId}")
	public void salary(@PathVariable("polisherId") Long polisherId) {

	}

	@Autowired
	private SalaryService salaryService;

	// Display salary calculation page to the manager only on the last 5 days of the
	// month

	@PostMapping("/salary/calculate")
	public String calculateSalaryForPolishers(Model model,
			@RequestParam(value = "polisherId", required = false) Long polisherId,
			@RequestParam(value = "managerId", required = false) Long managerId) {
		LocalDate currentDate = LocalDate.now();

		// Check if the current date is in the last 5 days of the month
		LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
		int lastFiveDaysStart = lastDayOfMonth.getDayOfMonth() - 11;
		System.out.println("Hello World How are you.......................");

		if (currentDate.getDayOfMonth() >= lastFiveDaysStart
				&& currentDate.getDayOfMonth() <= lastDayOfMonth.getDayOfMonth()) {
			// If it's the last 5 days, calculate and save salary
			System.out.println("Hello World How are you.......................I From Here ");
			salaryService.calculateAndSaveSalary(polisherId, currentDate); // Assuming departmentId = 1 for simplicity

			model.addAttribute("message", "Salary has been calculated for all polishers.");
		} else {
			model.addAttribute("message", "Salary calculation is only available in the last 5 days of the month.");
		}

		return "redirect:/manager/" + managerId;
	}
}
