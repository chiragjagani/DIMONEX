package com.stpl.dimonex.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Role;

@Repository
public class RoleDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Create a role
    @Transactional
    public Role createRole(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        hibernateTemplate.save(role);
        return role;
    }

    // Fetch all roles
    public List<Role> getAllRoles() {
        return hibernateTemplate.loadAll(Role.class);
    }

    // Find a role by ID
    public Optional<Role> findRoleById(Long id) {
        return Optional.ofNullable(hibernateTemplate.get(Role.class, id));
    }
}
