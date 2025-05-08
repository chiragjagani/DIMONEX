package com.stpl.dimonex.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.ManagerDao;
import com.stpl.dimonex.dao.UserDao;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.User;

@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDAO;

    @Autowired
    private UserDao userDAO;
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Create Manager and associate with User
    @Transactional
    public void createManager(String username, String password, String email, Double expenses) {
//        managerService.createManager(username, password, email, 0.0);  // Create Manager

    	User user = userDAO.createUser(username, password, email, 2L);  // Assuming role ID for Manager is 1
        Manager manager=new Manager();
        manager.setExpenses(expenses);
        manager.setUser(user);
        manager.setDepartment(1);
        managerDAO.saveManager(manager);
    }

    // Fetch Manager by User ID
    public Manager getManagerByUserId(Long userId) {
        return managerDAO.getManagerById(userId);
    }
    
    
    public List<Manager> getAllManagers() {
        return (List<Manager>) managerDAO.getAllManagers();
    }
    
 // Delete Manager
    @Transactional
    public void deleteManager(Long id) {
        Manager manager = hibernateTemplate.get(Manager.class, id);

        if (manager != null) {
            // Delete associated Polishers before deleting the Manager
            hibernateTemplate.execute(session -> {
                Query query = session.createQuery("DELETE FROM Polisher WHERE manager.id = :managerId");
                query.setParameter("managerId", id);
                return query.executeUpdate();
            });

            // Now, delete the manager
            hibernateTemplate.delete(manager);
        }
    }

    
 // Get Managers by Department
    @Transactional(readOnly = true)
    public List<Manager> getManagersByDepartment(int departmentId) {
        return managerDAO.getManagersByDepartment(departmentId);
    }
    
}
