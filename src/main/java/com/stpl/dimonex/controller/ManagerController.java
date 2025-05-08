//package com.stpl.dimonex.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.stpl.dimonex.dao.ManagerDao;
//import com.stpl.dimonex.model.Manager;
//
//@Controller
//@RequestMapping("/manager")
//public class ManagerController {
//
//    @Autowired
//    private ManagerDao managerDAO;
//
//    // View all managers
//    @GetMapping("/list")
//    public String getAllManagers(Model model) {
//        List<Manager> managers = managerDAO.getAllManagers();
//        model.addAttribute("managers", managers);
//        return "managers";
//    }
//
//    // Add a new manager
//    @GetMapping("/add")
//    public String showAddManagerPage() {
//        return "addManager";
//    }
//
//    // Save a manager
//    @RequestMapping("/save")
//    public String saveManager(@RequestParam("name") String name, @RequestParam("department") String department) {
//        Manager manager = new Manager(name, department);
//        managerDAO.saveManager(manager);
//        return "redirect:/manager/list";
//    }
//}


package com.stpl.dimonex.controller;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stpl.dimonex.mail.api.SalaryPDFGenerator;
import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.EquipmentAssignment;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.ManagerSalary;
import com.stpl.dimonex.model.Order;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.Salary;
import com.stpl.dimonex.model.Task;
import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.EquipmentService;
import com.stpl.dimonex.service.ManagerSalaryService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.OrderService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.SalaryService;
import com.stpl.dimonex.service.TaskService;
import com.stpl.dimonex.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PolisherService polisherService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private SalaryService salaryService;
    
    @Autowired
    private ManagerSalaryService managerSalaryService;
    
    
    @Autowired
    private EquipmentService equipmentService;

    // Display all extension requests
    @GetMapping("/extensionRequests")
    public String viewExtensionRequests(Model model) {
        model.addAttribute("extensionRequests", taskService.getTasksWithExtensionRequested());
        return "manager_approval"; // JSP page
    }

    // Handle approval or rejection of deadline extension
    @PostMapping("/approveExtension")
    public String approveExtension(@RequestParam("taskId") Long taskId,
                                   @RequestParam("decision") String decision) {
        Task task = taskService.getTaskById(taskId);
        
        if (task != null) {
            if ("approved".equals(decision)) {
                task.setDeadline(task.getRequestedExtensionDeadline().toString());
                task.setManagerResponse("Approved");
            } else {
                task.setManagerResponse("Rejected");
            }
            task.setExtensionRequested(false);
            taskService.updateTask(task);
        }
        return "redirect:/manager/"+task.getManager().getId();
    }
    
    @RequestMapping("/dashboard")
    public String showDashboard(Model model) {
       
        return "managerDashboard";
    }
    
    
    @GetMapping("/{id}")
    public String getManagerById(@PathVariable Long id, Model model, @RequestParam(value = "attendanceDate", required = false) String attendanceDate) {
        Manager manager = managerService.getManagerByUserId(id);
        System.out.println(manager);
        Department department=(Department) departmentService.getDepartmentById(manager.getDepartment());
        System.out.println(department);
      //  Map<Integer,Integer>salary=new 
        List<Polisher> polishers=polisherService.getAllPolishers();
        
        List<Polisher> pop=new ArrayList<>();
        
        Calendar c = Calendar.getInstance(); 
   	 int month=c.get(Calendar.WEEK_OF_MONTH);
        int year=c.get(Calendar.YEAR);
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = YearMonth.of(year, month).atEndOfMonth();
        
        System.out.println(lastDayOfMonth+"----------------------"+today);
        
        
        
        
        
        
        List<Salary>salaryAll=salaryService.getAllSalaries();
        System.out.println(salaryAll);
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (attendanceDate == null || attendanceDate.isEmpty()) {
            attendanceDate = LocalDate.now().toString();
        }
        
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("attendanceDate", attendanceDate); 	
        
        for(Polisher polish:polishers)
        {
        	if(polish.getManager().getId()==id) {
        		pop.add(polish);
        	}
        }
        
        
        List<Task> allTask=taskService.getAllTasks();
        
//        System.out.println("All The Tasks : "+allTask.get(0).getManager().getId());
        
        List<Task> assignedTask=new ArrayList<>();
        for(Task task:allTask) {
        	if(task.getManager().getId()==id) {
        	assignedTask.add(task);
        	}
        }
//        
        
        
        LocalDate currentDate = LocalDate.now();
        boolean isLastFiveDays = currentDate.isAfter(lastDayOfMonth.minusDays(5)) || currentDate.isEqual(lastDayOfMonth.minusDays(5));

        // Add the result to the model to conditionally render the salary table
        model.addAttribute("isLastFiveDays", isLastFiveDays);
       
//        System.out.println("Task Assigned to polishers : "+assignedTask.get(0).getManager().getId());
        model.addAttribute("extensionRequests", taskService.getTasksWithExtensionRequested());
        model.addAttribute("polisher", pop);
        model.addAttribute("manager", manager);
        model.addAttribute("department", department);
        model.addAttribute("tasks", assignedTask);
        model.addAttribute("userId",id);
        model.addAttribute("salaryAll",salaryAll);
        ManagerSalary managerSalary = managerSalaryService.getSalaryById(id);
        model.addAttribute("managerSalary", managerSalary);
        return "managerDashboard";  // Corresponding JSP page for manager details
    }
    
    
    @GetMapping("/listOrder/{id}")
    public String listOrder(@PathVariable Long id,Model model) {
    	
    	
      
    	List<Order> orders=orderService.getAllOrders();
    	
   
    	
    	Manager manager=managerService.getManagerByUserId(id);
    	
    	  List<Order> managerOrders = orders.stream()
                  .filter(order -> order.getManager().getId().equals(manager.getId()))
                  .collect(Collectors.toList());
    	  
    	  
    	  model.addAttribute("managerOrders",managerOrders);
    	
    	return "listOrderManager";
    }
    
    @PostMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId,
                                    @RequestParam("newStatus") String newStatus,Model model) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            orderService.updateOrderStatus(orderId, newStatus);
        }
        model.addAttribute("id", order.getManager().getId());
        return "redirect:/manager/listOrder/" + order.getManager().getId();
    }
    
    @GetMapping("/equipment")
    public String showDashboardEqipment(Model model) {
        List<Equipment> availableEquipment = equipmentService.getAvailableEquipment();
        List<Polisher> polishers = polisherService.getAllPolishers();
        List<EquipmentAssignment> assignments = equipmentService.getAllAssignments();

        model.addAttribute("availableEquipment", availableEquipment);
        model.addAttribute("polishers", polishers);
        model.addAttribute("assignments", assignments);

        return "Equipment";  // This maps to manager-dashboard.jsp
    }
    @PostMapping("/downloadSalarySlip")
    public void downloadSalarySlip(@RequestParam("managerId") Long managerId, HttpServletResponse response) {
        try {
            ManagerSalary salary = managerSalaryService.getSalaryById(managerId);

            if (salary == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Salary not found");
                return;
            }

            byte[] pdf = SalaryPDFGenerator.generateManagerSalarySlip(salary);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=salary-slip-" + salary.getMonth() + "-" + salary.getYear() + ".pdf");
            response.setContentLength(pdf.length);

            response.getOutputStream().write(pdf);
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF");
            } catch (Exception ignored) {}
        }
    }

    

}

