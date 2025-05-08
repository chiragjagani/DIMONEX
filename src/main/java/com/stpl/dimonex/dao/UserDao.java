package com.stpl.dimonex.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Role;
import com.stpl.dimonex.model.User;

@Repository
public class UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Create a user
    @Transactional
    public User createUser(String username, String password, String email, Long roleId) {
        Role role = hibernateTemplate.get(Role.class, roleId);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In a real app, hash the password
        user.setEmail(email);
        user.setRole(role);
        hibernateTemplate.save(user);
        return user;
    }

    // Get user by ID
    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(hibernateTemplate.get(User.class, userId));
    }
    
    
    // Authenticate User by username and password using named parameters
    public User authenticateUser(String username, String password) {
        // Use named parameters for clarity and readability
    	  Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
          
          // Create the query with named parameters
          String hql = "FROM com.stpl.dimonex.model.User u WHERE u.username = :username AND u.password = :password";
          
          // Create the query
          Query<User> query = session.createQuery(hql, User.class);
          
          // Set the parameters with named values
          query.setParameter("username", username);
          query.setParameter("password", password);
          
          // Execute the query and get the result list
          List<User> users = query.getResultList();
          
          // Return the first user found, or null if no user is found
          return (users.isEmpty()) ? null : users.get(0);
    }    
    
    
    public List<User> getAllUsers() {
        return (List<User>) hibernateTemplate.find("from User");
    }
    
}
