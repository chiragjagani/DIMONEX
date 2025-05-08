package com.stpl.dimonex.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.ManagerDao;
import com.stpl.dimonex.dao.PolisherDao;
import com.stpl.dimonex.dao.UserDao;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.Task;

@Service
public class PolisherService {

    @Autowired
    private PolisherDao polisherDAO;

    
    @Autowired
    private ManagerDao managerDAO;
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    TaskService taskService;
    
    
    @Autowired
    private UserDao userDAO;

    // Create Polisher and associate with User
//    public void createPolisher(String username, String password, String email, String skillLevel, String experienceLevel, Boolean availableStatus) {
//        User user = userDAO.createUser(username, password, email, 2L);  // Assuming role ID for Polisher is 2
//        Polisher ss=new Polisher();
//        ss.setSkillLevel(skillLevel);
//        ss.setAvailableStatus(availableStatus);
//        ss.setUser(user);
//        polisherDAO.savePolisher(ss);
//    }
    
    public List<Polisher> getAllPolishers() {
        return (List<Polisher>) polisherDAO.getAllPolishers();
    }

    
    
    public Polisher createPolisher(Polisher polisher, int departmentId) {
        // Step 1: Find the manager with the least number of polishers in the given department.
        Manager manager = findManagerWithLeastPolishers(departmentId);
        
        if (manager != null) {
            // Step 2: Assign the manager to the Polisher.
            polisher.setManager(manager);
            polisher.setDepartment_id(departmentId);
            
            // Step 3: Save the Polisher to the database.
             polisherDAO.savePolisher(polisher);
             return polisher;
        } else {
            throw new RuntimeException("No manager found for the given department.");
        }
    }
    
    
    
    
    public Polisher savePolisher(Polisher polisher) {
        // Step 1: Find the manager with the least number of polishers in the given department.
//        Manager manager = findManagerWithLeastPolishers(departmentId);
        
//        if (manager != null) {
            // Step 2: Assign the manager to the Polisher.
//            polisher.setManager(manager);
//            polisher.setDepartment_id(departmentId);
            
            // Step 3: Save the Polisher to the database.
             polisherDAO.savePolisher(polisher);
             return polisher;
        
    }

    
    private Manager findManagerWithLeastPolishers(int departmentId) {
        // Step 4: Query to get the manager with the least number of polishers in the department.
        List<Manager> managers = managerDAO.getManagersByDepartment(departmentId);

        Manager managerWithLeastPolishers = null;
        int leastPolishers = Integer.MAX_VALUE;

        for (Manager manager : managers) {
            // Get the count of polishers assigned to this manager
            long polishersCount = polisherDAO.countPolishersByManagerAndDepartment(manager.getId(), departmentId);
            
            if (polishersCount < leastPolishers) {
                leastPolishers = (int) polishersCount;
                managerWithLeastPolishers = manager;
            }
        }

        return managerWithLeastPolishers;
    }
    
    
    public long getPolishersByManager(Long managerId, int departmentId) {
        // Count Polishers assigned to the given manager in the specific department
        return polisherDAO.countPolishersByManagerAndDepartment(managerId, departmentId);
    }
    
    
    // Fetch Polisher by User ID
    public Polisher getPolisherByUserId(Long userId) {
        return polisherDAO.getPolisherById(userId);
    }
    
    @Transactional
    public void deletePolisher(Long id) {
        Polisher polisher = hibernateTemplate.get(Polisher.class, id);

        if (polisher != null) {
            // Delete associated tasks
            hibernateTemplate.execute(session -> {
                Query query = session.createQuery("DELETE FROM Task WHERE polisher.id = :polisherId");
                query.setParameter("polisherId", id);
                return query.executeUpdate();
            });

            // Now, delete the polisher
            hibernateTemplate.delete(polisher);
        }
    }

    public long getDiamondsCompletedThisMonth(Long id)
    {
    	List<Task>AllTasks=taskService.getAllTasks();
    	long dimondCompleted=0;
    	for(Task task:AllTasks)
    	{
    		if(task.getPolisher().getId()==id && task.getTrack().equals("Completed")) {
    			dimondCompleted+=task.getNumberOfDiamonds();
    		}
    	}
    	return dimondCompleted;
    }
    
    
    
}
