package com.stpl.dimonex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dimonex.dao.DepartmentDao;
import com.stpl.dimonex.model.Department;

@Service
public class DepartmentService {
	@Autowired
    private DepartmentDao departmentDAO;

    // Constructor to inject the DAO dependency
  

    // Add department
    public void addDepartment(Department department) {
        departmentDAO.saveDepartment(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    // Get department by ID
    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    // Delete department by ID
    public boolean deleteDepartment(int id) {
        return departmentDAO.deleteDepartment(id);
    }

    
    public double getDiamondPrice(int departmentId) {
        Department department = departmentDAO.getDepartmentById(departmentId);
        if (department != null) {
            return department.getDiamondPrice(); // Assuming diamondPrice is a field in the Department model
        }
        return 0.0; // Return 0 if department is not found
    }
    
    // Update department
    public boolean updateDepartment(Department department) {
        return departmentDAO.updateDepartment(department);
    }
}
