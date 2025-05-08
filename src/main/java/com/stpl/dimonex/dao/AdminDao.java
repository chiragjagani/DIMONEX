package com.stpl.dimonex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Admin;
import com.stpl.dimonex.model.User;

@Repository
public class AdminDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Create Admin and associate with User
    @Transactional
    public Admin createAdmin(User user) {
        Admin admin = new Admin();
        admin.setUser(user);

        // Save the Admin entity
        hibernateTemplate.saveOrUpdate(admin);
        return admin;
    }

    // Fetch Admin by User ID
    public Admin getAdminByUserId(Long userId) {
        String hql = "FROM Admin a WHERE a.user.id = :userId";
        List<Admin> admins = (List<Admin>) hibernateTemplate.findByNamedParam(hql, "userId", userId);
        return admins.isEmpty() ? null : admins.get(0);
    }
}
