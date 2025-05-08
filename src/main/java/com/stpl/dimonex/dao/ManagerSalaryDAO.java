package com.stpl.dimonex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.ManagerSalary;

@Repository
public class ManagerSalaryDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public void saveManagerSalary(ManagerSalary managerSalary) {
		hibernateTemplate.saveOrUpdate(managerSalary);
	}

	@Transactional
	public void updateManagerSalary(ManagerSalary managerSalary) {
		hibernateTemplate.update(managerSalary);
	}

	@Transactional
	public List<ManagerSalary> getAllManagerSalaries() {
		return hibernateTemplate.loadAll(ManagerSalary.class);
	}

	@Transactional
	public ManagerSalary getManagerSalaryById(Long id) {
		return hibernateTemplate.get(ManagerSalary.class, id);
	}

	// Fetch all salaries for a particular month and year
    public List<ManagerSalary> getManagerSalariesByMonthYear(int month, int year) {
        return (List<ManagerSalary>) hibernateTemplate.find(
            "from ManagerSalary where month = ?0 and year = ?1", month, year
        );
    }

	// Fetch salary for a specific manager for a specific month and year
	public ManagerSalary getSalaryForManagerByMonthYear(Long managerId, int month, int year) {
		List<ManagerSalary> result = (List<ManagerSalary>) hibernateTemplate
				.find("from ManagerSalary where manager.id = ?0 and month = ?1 and year = ?2", managerId, month, year);
		return result.isEmpty() ? null : result.get(0);
	}

}
