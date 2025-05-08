package com.stpl.dimonex.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Task;

@Repository
public class TaskDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Save a new Task or update an existing one
    @Transactional
    public void saveTask(Task task) {
        hibernateTemplate.saveOrUpdate(task);
    }

    // Get all Tasks
    public List<Task> getAllTasks() {
        return (List<Task>) hibernateTemplate.find("from Task");
    }

    // Get Task by Task ID
    public Task getTaskById(Long taskId) {
        return hibernateTemplate.get(Task.class, taskId);
    }

    // Update a Task
    @Transactional
    public void updateTask(Task task) {
        hibernateTemplate.update(task);
    }

    // Delete a Task by Task ID
    @Transactional
    public void deleteTask(Long taskId) {
        Task task = hibernateTemplate.get(Task.class, taskId);
        if (task != null) {
            hibernateTemplate.delete(task);
        }
    }

    // Get tasks assigned to a specific Polisher
    @Transactional
    public List<Task> getTasksByPolisherId(Long polisherId) {
        String hql = "from Task t where t.polisher.userId = :polisherId";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query<Task> query = session.createQuery(hql, Task.class);
        query.setParameter("polisherId", polisherId);
        return query.getResultList();
    }

    // Get tasks assigned by a specific Manager
    @Transactional
    public List<Task> getTasksByManagerId(Long managerId) {
        String hql = "from Task t where t.manager.userId = :managerId";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query<Task> query = session.createQuery(hql, Task.class);
        query.setParameter("managerId", managerId);
        return query.getResultList();
    }

    // Get tasks that are in a specific track status (e.g., "In Progress", "Completed")
    @Transactional
    public List<Task> getTasksByTrackStatus(String trackStatus) {
        String hql = "from Task t where t.track = :trackStatus";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query<Task> query = session.createQuery(hql, Task.class);
        query.setParameter("trackStatus", trackStatus);
        return query.getResultList();
    }

    // Get tasks by extension request (e.g., tasks that have requested an extension)
    @Transactional
    public List<Task> getTasksWithExtensionRequested() {
        String hql = "from Task t where t.extensionRequested = true";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query<Task> query = session.createQuery(hql, Task.class);
        return query.getResultList();
    }

    // Get tasks assigned to a Polisher and filtered by the deadline date range
    @Transactional
    public List<Task> getTasksByPolisherAndDeadline(Long polisherId, String startDate, String endDate) {
        String hql = "from Task t where t.polisher.userId = :polisherId and t.deadline between :startDate and :endDate";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query<Task> query = session.createQuery(hql, Task.class);
        query.setParameter("polisherId", polisherId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
    
    
    
    
    public List<Task> getAllExtensionRequests() {
        return (List<Task>) hibernateTemplate.find("FROM Task WHERE extensionRequested = true");
    }
    
    
    @Transactional
    public void updateTaskStatus(Long taskId, String status) {
    	 Task task = hibernateTemplate.get(Task.class, taskId);  // Fetch task by ID
    	 System.out.println("updateTaskStatus   Inside the dao  1");
         if (task != null) {
        	 
        	 System.out.println("updateTaskStatus   Inside the dao  2");
             task.setTrack(status); // Update status
             hibernateTemplate.update(task); // Save changes
         }
    }
}
