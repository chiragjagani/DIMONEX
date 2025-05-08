package com.stpl.dimonex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stpl.dimonex.mail.api.EmailService;
import com.stpl.dimonex.model.Admin;
import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.User;
import com.stpl.dimonex.service.AdminService;
import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PolisherService polisherService;

    @Autowired
    private AdminService adminService;

    @Autowired
	EmailService email;

    
    @Autowired
    private DepartmentService departmentService;

    // Display Login Page
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Handle Login form submission
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{"+authentication+"}}}}}}}}}}}}}}}}}}}}}}}}}}");
        
        User user = userService.authenticateUser(username, password);
        // Assume authenticateUser method exists in UserService
//        User user=null;
        System.out.println(user.getUsername()+"    "+user.getPassword()+ user.getRole().getName());
        if (user != null) {
            if (user.getRole().getName().equals("Manager")) {
            //	List<Manager> polisher=managerService.
            	List<Manager> mana=managerService.getAllManagers();
            	for(Manager manager:mana)
            	{
            		if(manager.getUser().getId()==user.getId()) {
            			 return "redirect:/manager/"+manager.getId();
            		}
            	}
                return "redirect:/manager/"+"1";
            } else if (user.getRole().getName().equals("Polishers")) {
            	List<Polisher> polisher=polisherService.getAllPolishers();
            	
            	for(Polisher polish:polisher)
            	{
            		if(polish.getUser().getId()==user.getId()) {
            			email.sendSimpleEmail("parthranipa31102003@gmail.com", "Hello parth", "Good Night!!!!!!!!!!!!");
            			return "redirect:/polisher/dashboard/"+polish.getId();
            		}
            	}
            	
                
            } else if (user.getRole().getName().equals("Admin")) {
            	Admin admin = adminService.getAdminByUserId(user.getId());
            	System.out.println("---------------------------------------------------------------");
            	return "redirect:/admin/dashboard?userId=" + admin.getId();
            }
        }
        
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    // Display Registration Page
    @RequestMapping("/register")
    public String registerPage(Model model) {
    	List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "register";
    }

    // Handle Registration form submission
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("role") String role,@RequestParam("department") String dept, Model model) {
    System.out.println(dept);
        if ("manager".equalsIgnoreCase(role)) {
        	userService.createUser(username, password, email,2L);  // Create Manager
            model.addAttribute("role", "manager");
            managerService.createManager(username, password, email, 1000.00);
        } else if ("polisher".equalsIgnoreCase(role)) {
        	User user=userService.createUser(username, password, email,3L);  // Create Polisher
        	Polisher polisher=new Polisher();
        	polisher.setUser(user);
        	polisherService.createPolisher(polisher,Integer.parseInt(dept));
            model.addAttribute("role", "polisher");
        } else if ("admin".equalsIgnoreCase(role)) {
            adminService.createAdmin(username, password, email);  // Create Admin
            model.addAttribute("role", "admin");
        }
        
        model.addAttribute("message", "Registration successful!");
        return "login";
    }

    // Handle logout (clear session)
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
