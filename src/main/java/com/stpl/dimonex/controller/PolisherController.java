package com.stpl.dimonex.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stpl.dimonex.model.Attendance;
import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Equipment;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.Task;
import com.stpl.dimonex.model.User;
import com.stpl.dimonex.service.AttendanceService;
import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.EquipmentService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.SalaryService;
import com.stpl.dimonex.service.TaskService;
import com.stpl.dimonex.service.UserService; 
@Controller
public class PolisherController {

    @Autowired
    private PolisherService polisherService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DepartmentService departmentService; 
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private EquipmentService equipmentService;
    
    
    @Autowired
    private SalaryService salaryService;

    // Display Dashboard with Polishers
    @RequestMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Polisher> polishers = polisherService.getAllPolishers();
        model.addAttribute("polishers", polishers);
        return "dashboard";
    }

    // Add new Polisher
    @RequestMapping(value = "/polisher/add", method = RequestMethod.POST)
    public String addPolisher(@ModelAttribute Polisher polisher) {
        polisherService.createPolisher(polisher, polisher.getDepartment_id());
        return "redirect:/dashboard";
    }

    @GetMapping("/polisher/dashboard/{polisherId}")
    public String getPolisherDashboard(@PathVariable("polisherId") Long polisherId, Model model) {
        // Fetch polisher profile
        Polisher polisher = polisherService.getPolisherByUserId(polisherId);
        
        if (polisher == null) {
            return "error"; // Return an error page if polisher is not found
        }

        System.out.println("Inside the controller: " + polisher);
        
        // Fetch attendance details
        Long userId = polisher.getUser().getId();
        System.out.println("This is the polisher's user ID: " + userId);
        List<Attendance> attendanceList = attendanceService.getAttendanceHistoryByUserAndMonth(userId, "03");

        User user = polisher.getUser();
        Department department = (Department) departmentService.getDepartmentById(polisher.getDepartment_id());

        // Fetch assigned equipment
        List<Equipment> assignedEquipment = equipmentService.getEquipmentByPolisher(polisherId);
        
        if (!assignedEquipment.isEmpty()) {
            System.out.println("DATE------------" + assignedEquipment.get(0).getAssignedDate());
        } else {
            System.out.println("No equipment assigned to this polisher.");
        }

        // Fetch and filter tasks assigned to this polisher
        List<Task> tasks = taskService.getAllTasks();
        List<Task> assignTask = new ArrayList<>();
        List<Task> salaryTask = new ArrayList<>();
        int salary = 0;

        System.out.println("-----TASK SIZE----" + tasks.size());

        for (Task task : tasks) {
            if (task.getPolisher() != null && task.getPolisher().getId().equals(polisherId)) {
                assignTask.add(task);
                
                // Calculate salary based on completed tasks
                String date = task.getDeadline();
                String[] dateParts = date.split("-");
                int month = Integer.parseInt(dateParts[1]);

                Calendar c = Calendar.getInstance();
                int currentMonth = c.get(Calendar.MONTH) + 1; // Java Calendar months are 0-based
                
                if (currentMonth == month && "Completed".equals(task.getTrack())) {
                    salary += (task.getNumberOfDiamonds() * task.getDepartment().getDiamondPrice());
                    
                }
            }
        }

        // Adding the fetched data to the model
        model.addAttribute("assignTasks", assignTask);
        model.addAttribute("polisher", polisher);
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("user", user);
        model.addAttribute("department", department);
        model.addAttribute("salary", salary);
        model.addAttribute("equipmentList", assignedEquipment);

        return "dashboard"; // Returns the JSP page for the dashboard
    }

    
    
    @RequestMapping("/polisher/dashboard/attendance")
    public String viewAttendance(HttpServletRequest request,@RequestParam(value = "polisherId", required = false)Long polisherId, @RequestParam(value = "month", required = false) String month, @RequestParam(value = "year", required = false) String year, Model model) {
        // Default to current month and year if not provided
    	System.out.print("Inside the viewAAttandance  "+polisherId);
    	
    	Polisher polisher=polisherService.getPolisherByUserId((polisherId));
    	
    	 User user=polisher.getUser();
         System.out.println(user);
         Department department=(Department) departmentService.getDepartmentById(polisher.getDepartment_id());
         
    	
        if (month == null) {
            month = String.format("%02d", LocalDate.now().getMonthValue()); // Current month
        }
        if (year == null) {
            year = String.valueOf(LocalDate.now().getYear()); // Current year
        }

        // Final or effectively final variables
        final String finalMonth = month;
        final String finalYear = year;

        // Fetch attendance records for the current polisher
//        Polisher polisher = (Polisher) request.getAttribute("polisher");
        List<Attendance> allAttendance = attendanceService.getAttendanceForPolisher(polisher.getUser().getId());
//        System.out.println("Inside the viewAAttandance  "+allAttendance);s
        // Filter attendance based on month and year
        List<Attendance> filteredAttendance = allAttendance.stream()
            .filter(attendance -> {
                String attendanceMonth = attendance.getDate().substring(5, 7);
                String attendanceYear = attendance.getDate().substring(0, 4);
                return attendanceMonth.equals(finalMonth) && attendanceYear.equals(finalYear);
            })
            .collect(Collectors.toList());

        // Pass filtered data to the JSP
        model.addAttribute("polisher", polisher);
        model.addAttribute("attendanceList", filteredAttendance);
        model.addAttribute("selectedMonth", finalMonth);
        model.addAttribute("selectedYear", finalYear);
        model.addAttribute("department", department);
        return "dashboard";
    }


    @PostMapping("/updatePolisher")
    public String updatePolisher(@RequestParam Long polisherId,
                                 @RequestParam String name,
                                 @RequestParam String status,
                                 @RequestParam String skillLevel,
                                 @RequestParam String experience,
                                 RedirectAttributes redirectAttributes) {
        // Fetch the existing polisher
        Polisher polisher = polisherService.getPolisherByUserId(polisherId);

        if (polisher != null) {
            // Update fields
            polisher.getUser().setUsername(name);
            polisher.setAvailableStatus(status.equalsIgnoreCase("Active")?true:false);
            polisher.setSkillLevel(skillLevel);
            polisher.setExperienceLevel(experience);

            // Save changes
            polisherService.savePolisher(polisher);

            // Success message
            redirectAttributes.addFlashAttribute("successMessage", "Polisher details updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Polisher not found!");
        }

        // Redirect back to dashboard
        return "redirect:/polisher/dashboard/"+polisher.getId();
    }

}