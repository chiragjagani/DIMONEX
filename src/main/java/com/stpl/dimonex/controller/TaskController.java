package com.stpl.dimonex.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import com.stpl.dimonex.model.Task;
//import com.stpl.dimonex.service.TaskService;
//
//@Controller
//@RequestMapping("/tasks")
//public class TaskController {
//
//    @Autowired
//    private TaskService taskService;
//
//    // Display all tasks
//    @GetMapping
//    public String viewAllTasks(Model model) {
//        List<Task> tasks = taskService.getAllTasks();
//        model.addAttribute("tasks", tasks);
//        return "task-list"; // Corresponds to task-list.jsp
//    }
//
//    // Display the task creation form
//    @GetMapping("/create")
//    public String showTaskCreationForm(Model model) {
//        model.addAttribute("task", new Task()); // Provide an empty task to bind form data
//        return "task-create"; // Corresponds to task-create.jsp
//    }
//
//    // Handle task creation or update
//    @PostMapping("/save")
//    public String saveTask(@ModelAttribute("task") Task task, Model model) {
//        taskService.saveOrUpdateTask(task);
//        model.addAttribute("message", "Task saved/updated successfully");
//        return "redirect:/tasks"; // Redirect to the list of tasks
//    }
//
//    // Display task details
//    @GetMapping("/{taskId}")
//    public String viewTaskDetails(@PathVariable Long taskId, Model model) {
//        Task task = taskService.getTaskById(taskId);
//        if (task != null) {
//            model.addAttribute("task", task);
//            return "task-detail"; // Corresponds to task-detail.jsp
//        } else {
//            model.addAttribute("message", "Task not found");
//            return "error"; // Corresponds to error.jsp
//        }
//    }
//
//    // Show task edit form
//    @GetMapping("/edit/{taskId}")
//    public String showTaskEditForm(@PathVariable Long taskId, Model model) {
//        Task task = taskService.getTaskById(taskId);
//        if (task != null) {
//            model.addAttribute("task", task);
//            return "task-edit"; // Corresponds to task-edit.jsp
//        } else {
//            model.addAttribute("message", "Task not found");
//            return "error";
//        }
//    }
//
//    // Handle task deletion
//    @GetMapping("/delete/{taskId}")
//    public String deleteTask(@PathVariable Long taskId, Model model) {
//        taskService.deleteTask(taskId);
//        model.addAttribute("message", "Task deleted successfully");
//        return "redirect:/tasks"; // Redirect to the list of tasks
//    }
//
//    // Handle extension request approval/rejection
//    @PostMapping("/extension/{taskId}")
//    public String handleExtensionRequest(@PathVariable Long taskId, 
//                                         @RequestParam("extensionAccepted") boolean extensionAccepted, 
//                                         Model model) {
//        Task task = taskService.getTaskById(taskId);
//        if (task != null) {
//            task.setExtensionRequested(extensionAccepted);
//            taskService.saveOrUpdateTask(task);
//            String message = extensionAccepted ? "Extension granted" : "Extension denied";
//            model.addAttribute("message", message);
//        } else {
//            model.addAttribute("message", "Task not found");
//        }
//        return "redirect:/tasks"; // Redirect to the list of tasks
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.Task;
import com.stpl.dimonex.service.DepartmentService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.PolisherService;
import com.stpl.dimonex.service.TaskService;
import com.stpl.dimonex.service.UserService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

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

	// Create or Edit Task
	@GetMapping("/create/{managerId}")
	public String createTaskForm(@PathVariable("managerId") Long managerId,Model model) {
		model.addAttribute("task", new Task());

		Manager manager=managerService.getManagerByUserId(managerId);
		
		System.out.println(manager);
		List<Polisher> polishers=polisherService.getAllPolishers();
		List<Polisher>relatedPolisher=new ArrayList<>();
		
		System.out.println("THis is mangerr id   "+polishers.get(0).getManager().getId());
		for(Polisher polisher:polishers)
		{
			
			if(polisher.getManager().getId()==manager.getId()) {
				relatedPolisher.add(polisher);
			}
		}
		
//		System.out.println("manager department  :  "+manager.getDepartment());
		
		Department department=departmentService.getDepartmentById(manager.getDepartment());

		System.out.println("Department is : "+department);
		
		model.addAttribute("user", userService.getAllUsers());
		model.addAttribute("managers", manager);
		model.addAttribute("polishers", relatedPolisher);
		model.addAttribute("departments",department);
		return "task-create"; // task-create.jsp
	}

	@PostMapping("/save")
	public String saveOrUpdateTask(@RequestParam String taskName, @RequestParam String description,
			@RequestParam int dimonds,@RequestParam String deadline, @RequestParam String track, @RequestParam Long managerId,
			@RequestParam Long polisherId, @RequestParam int departmentId) {
		// Set the selected Manager, Polisher, and Department to the Task
		System.out.println(
				"this is task details      " + taskName + "      " + description + "    " + deadline + "  " + track+ "   "+dimonds);
		System.out.println(
				"managerId " + managerId + "  " + "polisherId " + polisherId + " " + "departmentId " + departmentId);
//    	, @ModelAttribute Task task dimonds
//        
		Task task=new Task();
		task.setTaskName(taskName);
		task.setDescription(description);
		task.setNumberOfDiamonds(dimonds);
		task.setDeadline(deadline);
		task.setDeadline(deadline);
		task.setTrack("Assigned");
	

        Manager manager = managerService.getManagerByUserId(managerId);
        Polisher polisher = polisherService.getPolisherByUserId(polisherId);
        Department department = departmentService.getDepartmentById(departmentId);
//        
        task.setManager(manager);
        task.setPolisher(polisher);
        task.setDepartment(department);
//        
        System.out.println(task);
        taskService.saveOrUpdateTask(task);
		return "redirect:/polisher/dashboard/"+polisherId;
	}
	
	

	
	
	
	@PostMapping("/requestExtension")
    public String requestExtension(@RequestParam("taskId") Long taskId,
                                   @RequestParam("newDeadline") String newDeadline, Model model) {
        Task task = taskService.getTaskById(taskId);

        if (task != null) {
            task.setRequestedExtensionDeadline(LocalDate.parse(newDeadline));
            task.setExtensionRequested(true);
            taskService.updateTask(task);
        }

        return "redirect:/polisher/dashboard/"+task.getPolisher().getId();
    }
	
	
	
	
	
	  @PostMapping("/updateTaskStatus")
	    public String updateTaskStatus(@RequestParam("taskId") Long taskId,
	    							   @RequestParam("polisherId") Long polisherId,
	                                   @RequestParam("status") String status,
	                                   RedirectAttributes redirectAttributes) {
	        try {
	            taskService.updateTaskStatus(taskId, status);
	            redirectAttributes.addFlashAttribute("successMessage", "Task status updated successfully!"); 
	            return "redirect:/polisher/dashboard/"+polisherId;  // Redirect back to the tasks page
	        } catch (Exception e) {
	        	
	        	System.out.println(e.getMessage());
	            redirectAttributes.addFlashAttribute("errorMessage", "Error updating task status: " + e.getMessage());
	        }
	       return null;
	    }
}
