package com.stpl.dimonex.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.RoleDao;
import com.stpl.dimonex.dao.UserDao;
import com.stpl.dimonex.model.Attendance;
import com.stpl.dimonex.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDAO;

    @Autowired
    private RoleDao roleDAO;

    // Create a user
    public User createUser(String username, String password, String email, Long roleId) {
       roleDAO.findRoleById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        return userDAO.createUser(username, password, email, roleId);
    }

    // Fetch user by ID
    public User getUser(Long userId) {
        return userDAO.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
 // Authenticate User (username and password)
    @Transactional
    public User authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();  // Method to get all users
    }
    
  
}
