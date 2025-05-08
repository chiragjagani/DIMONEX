package com.stpl.dimonex.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Department;

@Repository
public class DepartmentDao {

	 @Autowired
    private HibernateTemplate hibernateTemplate;


    // Method to save department
    @Transactional
    public void saveDepartment(Department department) {
    	System.out.println(department);
        hibernateTemplate.saveOrUpdate(department);
    }

    // Method to get all departments
    public List<Department> getAllDepartments() {
        return (List<Department>) hibernateTemplate.find("from Department");
    }

    // Method to get department by ID
    public Department getDepartmentById(int id) {
        return hibernateTemplate.get(Department.class, id);
    }

    // Method to delete department by ID
    public boolean deleteDepartment(int id) {
        Department department = getDepartmentById(id);
        if (department != null) {
            hibernateTemplate.delete(department);
            return true;
        }
        return false;
    }

    // Method to update department
    public boolean updateDepartment(Department updatedDepartment) {
        Department department = getDepartmentById(updatedDepartment.getId());
        if (department != null) {
            hibernateTemplate.update(updatedDepartment);
            return true;
        }
        return false;
    }
}
