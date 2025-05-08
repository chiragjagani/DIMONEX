package com.stpl.dimonex;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.stpl.dimonex.config.AppConfig;
//import com.stpl.dimonex.dao.DepartmentDao;
//import com.stpl.dimonex.dao.RoleDao;
//import com.stpl.dimonex.dao.UserDao;
//import com.stpl.dimonex.model.Department;
//import com.stpl.dimonex.model.Role;
//
//public class App {
//
//    public static void main(String[] args) {
//        // Create Spring context
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
//            // Get RoleDAO and UserDAO beans
////            RoleDAO roleDAO = context.getBean(RoleDAO.class);
////            UserDAO userDAO = context.getBean(UserDAO.class);
//
//            DepartmentDao departmentDao = context.getBean(DepartmentDao.class);
//            // Create a role
////            Role role = roleDAO.createRole("Polishers", "Polishers with no access");
////            System.out.println("Role created: " + role.getName());
//            departmentDao.saveDepartment(new Department( "Ghat", 5));
//            departmentDao.saveDepartment(new Department( "Polish", 8));
//            departmentDao.saveDepartment(new Department( "Mathala", 7));
//            departmentDao.saveDepartment(new Department( "Brushing", 7));
//            departmentDao.saveDepartment(new Department( "Inspection", 5));
//            
//            
//            
//            // Fetch all roles
////            System.out.println("All roles:");
////            roleDAO.getAllRoles().forEach(r -> System.out.println(r.getName()));
////
////            // Create a user
////            User user = userDAO.createUser("john_doe", "password123", "john.doe@example.com", role.getId());
////            System.out.println("User created: " + user.getUsername());
////
////            // Fetch the user by ID
////            User fetchedUser = userDAO.getUserById(user.getId()).orElse(null);
////            System.out.println("User fetched: " + (fetchedUser != null ? fetchedUser.getUsername() : "User not found"));
//        }
//    }
//}



import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stpl.dimonex.config.AppConfig;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.PolisherService;

public class App {

    public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the PolisherService bean
        PolisherService polisherService = context.getBean(PolisherService.class);
        ManagerService managerService = context.getBean(ManagerService.class);
//        // Create sample managers (in practice, these would be retrieved from the database)
//        Manager manager1 = new Manager();
//        manager1.setId(1L);
//        manager1.setDepartment(1);
//        manager1.setExpenses(1000.0); // Can be used to simulate manager expenses or criteria for selection
//        managerService.createManager("joh", "12345", "asd@gmail.com", 1000.0);
//        
////        public void createManager(String username, String password, String email, Double expenses) {
//
//        Manager manager2 = new Manager();
//        manager2.setId(2L);
//        manager2.setDepartment(1);
//        manager2.setExpenses(1500.0); // Different manager
//        managerService.createManager("Loban", "12345", "Loban@gmail.com", 1000.0);

        // Sample department ID for which we are creating a polisher
        int departmentId = 1;

        // Simulate polisher creation and assign the manager with the least polishers
        Polisher newPolisher = new Polisher();
        newPolisher.setSkillLevel("Advanced");
        newPolisher.setExperienceLevel("Top Most");
        newPolisher.setAvailableStatus(true);

        // Call the service to create the polisher and assign a manager
        try {
            Polisher createdPolisher = polisherService.createPolisher(newPolisher, departmentId);
            System.out.println("Polisher created with ID: " + createdPolisher.getId());
            System.out.println("Assigned to manager: " + createdPolisher.getManager().getId());

            // Verify that the correct manager was assigned (the one with fewer polishers)
            Long assignedManagerId = createdPolisher.getManager().getId();
            System.out.println("Assigned Manager ID: " + assignedManagerId);

            // Additional: Verify the manager's polishers count (can be retrieved from DAO if needed)
//            System.out.println("Manager 1 polishers: " + polisherService.getPolishersByManager(manager1.getId(),departmentId));
//            System.out.println("Manager 2 polishers: " + polisherService.getPolishersByManager(manager2.getId(),departmentId));
        } catch (RuntimeException e) {
            System.err.println("Error creating polisher: " + e.getMessage());
        }

        // Close the Spring context
        context.close();
    }
}