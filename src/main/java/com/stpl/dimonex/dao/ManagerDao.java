	package com.stpl.dimonex.dao;
	
	import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Manager;
	
	@Repository
	public class ManagerDao {
	
	    @Autowired
	    private HibernateTemplate hibernateTemplate;
	
	    // Save a new Manager
	    @Transactional
	    public void saveManager(Manager manager) {
	        hibernateTemplate.saveOrUpdate(manager);
	    }
	
	    // Get all Managers
//	    public List<Manager> getAllManagers() {
//	        return (List<Manager>) hibernateTemplate.find("from Manager");
//	    }
	    
	    @Transactional
	    public List<Manager> getAllManagers() {
	        // HQL query to fetch Manager and its associated User eagerly
	        String hql = "SELECT m FROM Manager m JOIN FETCH m.user";
	        
	        // Execute the query using HibernateTemplate's find method
	        List<Manager> managers = (List<Manager>) hibernateTemplate.find(hql);

	        return managers;
	    }
	
	    // Get Manager by ID
	    public Manager getManagerById(Long id) {
	        return hibernateTemplate.get(Manager.class, id);
	    }
	
	    // Update a Manager
	    public void updateManager(Manager manager) {
	        hibernateTemplate.update(manager);
	    }
	
	    // Delete a Manager
	    public void deleteManager(Long id) {
	    	 Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
	    	    Manager manager = session.get(Manager.class, id);
	    	    if (manager != null) {
	    	        System.out.println("Manager Found. Proceeding with Deletion.");
	    	        session.delete(manager);
	    	    } else {
	    	        System.out.println("Manager Not Found. Deletion Skipped.");
	    	    }
	    }
	    
	    @Transactional
	    public List<Manager> getManagersByDepartment(int departmentId) {
	    	 String hql = "from Manager m where m.department = :departmentId";
	
	         // Get current session
	         Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
	
	         // Create query and set parameter
	         Query query = session.createQuery(hql);
	         query.setParameter("departmentId", departmentId);
	
	         // Execute the query and return the list of managers
	         return query.list();
	         }
	}
