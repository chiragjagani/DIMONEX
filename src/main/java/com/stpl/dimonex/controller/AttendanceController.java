package com.stpl.dimonex.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stpl.dimonex.model.Attendance;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.User;
import com.stpl.dimonex.service.AttendanceService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.UserService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    
    @Autowired 
    private ManagerService managerService;
    // Show attendance form
    @GetMapping("/")
    public String showAttendancePage(HttpServletRequest request) {
        // Retrieve all users for the attendance form
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users); // Pass Users to the JSP
        return "attendance"; // Return the JSP page
    }

    // Submit attendance for each user
    @PostMapping("/submit")
    public String submitAttendance(HttpServletRequest request) {
        // Get current date
        Date currentDate = new Date(System.currentTimeMillis());
        String managerid=request.getParameter("manager");
        // Iterate over each user and save their attendance status
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            String status = request.getParameter("attendanceStatus_" + user.getId());
            
            System.out.print("------------MANAGER ID FROM ATTENDED_----"+managerid);
            if (status != null) {
                Attendance attendance = new Attendance();
                Date currentDate1 = new Date(System.currentTimeMillis());
                
                                // Define the format for the output string
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                                // Convert the Date to String
                                String dateString = dateFormat.format(currentDate1);
                //
                attendance.setDate(dateString);
                attendance.setStatus(status);
                attendance.setUserId(user.getId());
                
                
//               Manager manager=managerService.getManagerByUserId(user.getId());
//                managerId=manager.getId();
                

                // Save the attendance for the user
                attendanceService.addAttendance(attendance);
            }
        }

        return "redirect:/manager/"+managerid ; // Redirect back to the attendance page to see updated data
    }

    // Show attendance records for a specific date
    @GetMapping("/list")
    public String showAttendanceListPage(HttpServletRequest request) {
        // Get current date
    	Date currentDate = new Date(System.currentTimeMillis());
        
        // Define the format for the output string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Convert the Date to String
        String dateString = dateFormat.format(currentDate);

        // Retrieve all attendance records for today
        List<Attendance> attendances = attendanceService.getAttendanceByDate(dateString);
        request.setAttribute("attendances", attendances);

        // Calculate counts of "Present" and "Absent"
        long presentCount = attendances.stream().filter(attendance -> "Present".equals(attendance.getStatus())).count();
        long absentCount = attendances.stream().filter(attendance -> "Absent".equals(attendance.getStatus())).count();

        // Pass attendance summary data to the JSP
        request.setAttribute("attendanceDate", currentDate);
        request.setAttribute("presentCount", presentCount);
        request.setAttribute("absentCount", absentCount);

        return "attendanceList";  // Return the attendance list page
    }
    
    
    @GetMapping("/history")
    public String showAttendanceHistory(@RequestParam("userId") Long userId, 
                                        @RequestParam("month") String month, 
                                        Model model) {
        // Retrieve attendance records for the specific user and month
        List<Attendance> attendanceList = attendanceService.getAttendanceHistoryByUserAndMonth(userId, month);

        // Add the attendance list and selected month to the model
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("month", month);
        // Assuming you have a user object with name to show in the view
        model.addAttribute("user", userService.getUser(userId));

        return "attendanceHistory";  // Return the attendance history view
    }

    
    
    //temporary code
    @GetMapping("/att")
    public String showAttendancePage(Model model, @RequestParam(value = "attendanceDate", required = false) String attendanceDate) {
        // Retrieve all users (polishers) for the attendance form
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users); // Pass Users to the JSP

        // Set default to today if no date is passed
        if (attendanceDate == null || attendanceDate.isEmpty()) {
            attendanceDate = LocalDate.now().toString();
        }

        // Retrieve attendance records for the selected date
        List<Attendance> attendances = attendanceService.getAttendanceByDate(attendanceDate);
        model.addAttribute("attendances", attendances); // Pass attendance records to the JSP

        // Add current date for convenience
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("attendanceDate", attendanceDate); // Pass the selected date

        // Retrieve all managers (if needed for selection)
        List<Manager> managers = managerService.getAllManagers();
        model.addAttribute("managers", managers); // Pass Managers to the JSP

        return "attandancexyz"; // Return the JSP page
    }
    
    @GetMapping("/attendance-data")
    @ResponseBody
    public List<Attendance> getAttendanceData(@RequestParam("attendanceDate") String attendanceDate) {
        // Retrieve attendance records for the selected date
        return attendanceService.getAttendanceByDate(attendanceDate);
    }
    
    
//    @PostMapping("/attsubmit")
//    public String submitAttendance(HttpServletRequest request, @RequestParam("attendanceDate") String attendanceDate) {
//        // Get the manager's ID (the manager submitting attendance)
//        String managerid = request.getParameter("manager");
//        
//        // Retrieve all users (polishers) for attendance
//        List<User> users = userService.getAllUsers();
//        
//        // Parse the provided date for attendance
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = dateFormat.parse(attendanceDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Iterate over each user and save their attendance status for the selected date
//        for (User user : users) {
//            String status = request.getParameter("attendanceStatus_" + user.getId());
//            if (status != null) {
//                Attendance attendance = new Attendance();
//                attendance.setDate(attendanceDate);
//                attendance.setStatus(status);
//                attendance.setUserId(user.getId());
//                
//                // Save the attendance for the user
//                attendanceService.addAttendance(attendance);
//            }
//        }
//
//        return "redirect:/manager/" + managerid; // Redirect back to the manager page
//    }
    @PostMapping("/attsubmitadmin")
    public String submitAttendance3(HttpServletRequest request,@RequestParam("admin") Long manager) {
        // Get selected date for attendance
        String attendanceDate = request.getParameter("attendanceDate");
        List<User> users = userService.getAllUsers();

        System.out.println("Manager id is the $$$$$$--|||||||||||||-----------------------------------------"+manager);
        
        for (User user : users) {
            String status = request.getParameter("attendanceStatus_" + user.getId());
            
            if (status != null) {
                // Create or update attendance
                Attendance attendance = new Attendance();
                attendance.setDate(attendanceDate);
                attendance.setStatus(status);
                attendance.setUserId(user.getId());

                // Save or update the attendance record
                attendanceService.addOrUpdateAttendance(attendance);
            }
        }
        
        return "redirect:/admin/dashboard?userId="+manager;  // Redirect back to the attendance page
    }
    
    
    
    
    @PostMapping("/attsubmit")
    public String submitAttendance2(HttpServletRequest request,@RequestParam("manager") Long manager) {
        // Get selected date for attendance
        String attendanceDate = request.getParameter("attendanceDate");
        List<User> users = userService.getAllUsers();

        System.out.println("Manager id is the $$$$$$--|||||||||||||-----------------------------------------"+manager);
        
        for (User user : users) {
            String status = request.getParameter("attendanceStatus_" + user.getId());
            
            if (status != null) {
                // Create or update attendance
                Attendance attendance = new Attendance();
                attendance.setDate(attendanceDate);
                attendance.setStatus(status);
                attendance.setUserId(user.getId());

                // Save or update the attendance record
                attendanceService.addOrUpdateAttendance(attendance);
            }
        }
        
        return "redirect:/manager/"+manager;  // Redirect back to the attendance page
    }
    
 // Show attendance records for a specific date
    @GetMapping("/attlist")
    public String showAttendanceListPage(HttpServletRequest request, @RequestParam(value = "date", required = false) String date) {
        // Default to today's date if no date is provided
        if (date == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(new Date());
        }

        // Retrieve all attendance records for the selected date
        List<Attendance> attendances = attendanceService.getAttendanceByDate(date);
        request.setAttribute("attendances", attendances);

        // Calculate counts of "Present" and "Absent"
        long presentCount = attendances.stream().filter(attendance -> "Present".equals(attendance.getStatus())).count();
        long absentCount = attendances.stream().filter(attendance -> "Absent".equals(attendance.getStatus())).count();

        // Pass attendance summary data to the JSP
        request.setAttribute("attendanceDate", date);
        request.setAttribute("presentCount", presentCount);
        request.setAttribute("absentCount", absentCount);

        return "attendanceList";  // Return the attendance list page
    }
    
    @GetMapping("/atthistory")
    public String showAttendanceHistory2(@RequestParam("userId") Long userId, 
                                        @RequestParam("month") String month, 
                                        Model model) {
        // Retrieve attendance records for the specific user and month
        List<Attendance> attendanceList = attendanceService.getAttendanceHistoryByUserAndMonth(userId, month);

        // Add the attendance list and selected month to the model
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("month", month);
        // Assuming you have a user object with name to show in the view
        model.addAttribute("user", userService.getUser(userId));

        return "attendanceHistory";  // Return the attendance history view
    }
    
    
    
}
