package com.stpl.dimonex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.ManagerDao;
import com.stpl.dimonex.dao.TaskDao;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Task;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;
    
    @Autowired
    private ManagerDao managerDAO;

    // Save or update a Task
    public void saveOrUpdateTask(Task task) {
        taskDao.saveTask(task);
    }

    // Get Task by ID
    public Task getTaskById(Long taskId) {
        return taskDao.getTaskById(taskId);
    }

    // Get all Tasks
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    // Get tasks assigned to a specific Polisher
    public List<Task> getTasksByPolisherId(Long polisherId) {
        return taskDao.getTasksByPolisherId(polisherId);
    }

    // Get tasks assigned by a specific Manager
    public List<Task> getTasksByManagerId(Long managerId) {
        return taskDao.getTasksByManagerId(managerId);
    }

    // Update a Task
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Transactional
    public void updateTaskStatus(Long taskId, String status) {
    	System.out.println("Inside the tesk service broooooooooooooooooooooo");
        taskDao.updateTaskStatus(taskId, status);
    }
    
    
    // Delete a Task by ID
    public void deleteTask(Long taskId) {
        taskDao.deleteTask(taskId);
    }

    // Get tasks with an extension request
    public List<Task> getTasksWithExtensionRequested() {
        return taskDao.getTasksWithExtensionRequested();
    }
    public void assignTask(Long managerId, String taskName, String description, int numberOfDiamonds, String deadline, String track) {
        Manager manager = managerDAO.getManagerById(managerId);

        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setNumberOfDiamonds(numberOfDiamonds);
        task.setDeadline(deadline);
        task.setTrack(track);
        task.setManager(manager);

        taskDao.saveTask(task);;
    }
}
