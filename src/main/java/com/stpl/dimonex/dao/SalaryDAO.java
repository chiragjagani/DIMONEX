package com.stpl.dimonex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Salary;

@Repository
public class SalaryDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public void saveSalary(Salary salary) {
		hibernateTemplate.save(salary);
	}

	public List<Salary> getAllSalaries() {
		return hibernateTemplate.loadAll(Salary.class);
	}

	public Salary getSalaryById(int id) {
		return hibernateTemplate.get(Salary.class, id);
	}

	public List<Salary> getSalariesByMonthYear(int month, int year) {
		return (List<Salary>) hibernateTemplate.find("from Salary where salaryMonth = ?0 and salaryYear = ?1", month, year);
	}

}
