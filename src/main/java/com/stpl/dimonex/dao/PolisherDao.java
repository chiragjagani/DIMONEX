package com.stpl.dimonex.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Polisher;

@Repository
public class PolisherDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Save a new Polisher
    @Transactional
    public void savePolisher(Polisher polisher) {
        hibernateTemplate.saveOrUpdate(polisher);
    }

    // Get all Polishers
//    public List<Polisher> getAllPolishers() {
//        return (List<Polisher>) hibernateTemplate.find("from Polisher");
//    }

    @Transactional
    public List<Polisher> getAllPolishers() {
        // HQL query to fetch Manager and its associated User eagerly
        String hql = "SELECT m FROM Polisher m JOIN FETCH m.user";
        
        // Execute the query using HibernateTemplate's find method
        List<Polisher> managers = (List<Polisher>) hibernateTemplate.find(hql);

        return managers;
    }

    
    // Get Polisher by ID
    public Polisher getPolisherById(Long id) {
        return hibernateTemplate.get(Polisher.class, id);
    }

    // Update a Polisher
    public void updatePolisher(Polisher polisher) {
        hibernateTemplate.update(polisher);
    }

    // Delete a Polisher
    public void deletePolisher(Long id) {
        Polisher polisher = hibernateTemplate.get(Polisher.class, id);
        if (polisher != null) {
            hibernateTemplate.delete(polisher);
        }		 
    }		
    
    
    // Get a list of Polishers in a department
    @Transactional
    public List<Polisher> getPolishersByDepartment(Long departmentId) {
        return (List<Polisher>) hibernateTemplate.find("from Polisher where department_id = ?", departmentId);
    }
    
    
    @Transactional
    public List<Polisher> findPolishersByManagerAndMonth(Long managerId, Long departmentId, String month) {
        // Create the query to filter Polishers by manager, department, and month of assignment
        String hql = "from Polisher p where p.manager.id = :managerId and p.department_id = :departmentId " +
                     "and function('month', p.user.startDate) = :month and function('year', p.user.startDate) = function('year', current_date)";

        // Get the current session from Hibernate
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        // Create the query and set parameters
        Query<Polisher> query = session.createQuery(hql, Polisher.class);
        query.setParameter("managerId", managerId);
        query.setParameter("departmentId", departmentId);
        query.setParameter("month", Integer.parseInt(month));  // month should be a string like "01", "02", etc.

        // Execute the query and return the list of results
        return query.getResultList();
    }
    
    
    @Transactional
    public long countPolishersByManagerAndDepartment(Long managerId, int departmentId) {
        // Using execute method to run a query for counting polishers assigned to a manager in the department
        return hibernateTemplate.execute(session -> {
            String hql = "select count(p.id) from Polisher p where p.manager.id = :managerId and p.department_id = :departmentId";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("managerId", managerId);
            query.setParameter("departmentId", departmentId);
            return query.uniqueResult();
        });
    }
    
    
    
    @Transactional
    public List<Polisher> getPolishersPendingSalary(Long managerId, int month, int year) {
        // Fetch polishers who have not received salary for the current month and year
        String hql = "SELECT p FROM Polisher p WHERE p.manager.id = :managerId AND p.id NOT IN " +
                     "(SELECT s.polisherId FROM Salary s WHERE s.salaryMonth = :month AND s.salaryYear = :year AND s.isPaid = true)";
        return (List<Polisher>) hibernateTemplate.getSessionFactory().getCurrentSession()
            .createQuery(hql)
            .setParameter("managerId", managerId)
            .setParameter("month", month)
            .setParameter("year", year)
            .list();
    }

}
