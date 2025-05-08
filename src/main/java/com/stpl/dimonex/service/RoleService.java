package com.stpl.dimonex.service;

import com.stpl.dimonex.dao.RoleDao;
import com.stpl.dimonex.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDAO;

    // Create a role
    public Role createRole(String name, String description) {
        return roleDAO.createRole(name, description);
    }

    // Fetch all roles
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
