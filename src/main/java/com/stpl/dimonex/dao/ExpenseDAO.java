package com.stpl.dimonex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Expense;

@Repository
public class ExpenseDAO {

	@Autowired
    private HibernateTemplate hibernateTemplate;

	

    @Transactional
    public void saveExpense(Expense expense) {
        hibernateTemplate.save(expense);
    }


    public List<Expense> getAllExpenses() {
        return hibernateTemplate.loadAll(Expense.class);
    }

    
    public Expense getExpenseByMonthYear(int month, int year) {
        List<Expense> list = (List<Expense>) hibernateTemplate.find(
            "from CompanyExpense where month = ?0 and year = ?1", month, year
        );
        return list.isEmpty() ? null : list.get(0);
    }
    public boolean existsByMonthAndYear(int month, int year) {
        List<Expense> list = (List<Expense>) hibernateTemplate.find(
            "from Expense where month = ?0 and year = ?1", month, year
        );
        return !list.isEmpty();
    }
    
    
	
	
}
