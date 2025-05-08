package com.stpl.dimonex.service;

import com.stpl.dimonex.dao.AdminDao;
import com.stpl.dimonex.dao.UserDao;
import com.stpl.dimonex.model.Admin;
import com.stpl.dimonex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDAO;

    @Autowired
    private UserDao userDAO;

    // Create Admin and associate with User
    @Transactional
    public Admin createAdmin(String username, String password, String email) {
        User user = userDAO.createUser(username, password, email, 1L);  // Assuming role ID for Admin is 3
        return adminDAO.createAdmin(user);
    }

    // Fetch Admin by User ID
    public Admin getAdminByUserId(Long userId) {
        return adminDAO.getAdminByUserId(userId);
    }
}
