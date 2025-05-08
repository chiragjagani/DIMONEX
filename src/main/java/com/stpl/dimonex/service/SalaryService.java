package com.stpl.dimonex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.SalaryDAO;
import com.stpl.dimonex.model.Department;
import com.stpl.dimonex.model.Polisher;
import com.stpl.dimonex.model.Salary;
@Service
public class SalaryService {
	@Autowired
    private SalaryDAO salaryDAO;

	@Autowired
    private PolisherService polisherService;
    
    @Autowired
    private DepartmentService departmentService;
    
    public List<Salary> getSalariesByMonthYear(int month, int year) {
        return salaryDAO.getSalariesByMonthYear(month, year);
    }

   
    @Transactional
    public void calculateAndSaveSalary(Long polisherId, int departmentId, Long diamondsCompleted, int pricePerDiamond) {
        Long totalSalary = diamondsCompleted * pricePerDiamond;
//        Salary salary = new Salary(polisherId, departmentId, diamondsCompleted, totalSalary);
//        salaryDAO.saveSalary(salary);
        
    }

    
    public List<Salary> getAllSalaries() {
        return salaryDAO.getAllSalaries();
    }

  
    public Salary getSalaryById(int id) {
        return salaryDAO.getSalaryById(id);
    }
    
    
    @Transactional
    public void calculateAndSaveSalary(Long polisherId, LocalDate currentDate) {
        // Check if current date is within the last 5 days of the month
    	
    	System.out.println("Inside the salary service ::----");
        LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        int lastFiveDaysStart = lastDayOfMonth.getDayOfMonth() - 11;
        
//        if (currentDate.getDayOfMonth() >= lastFiveDaysStart && currentDate.getDayOfMonth() <= lastDayOfMonth.getDayOfMonth()) {
            Polisher polishers = polisherService.getPolisherByUserId(polisherId);
            Department department = departmentService.getDepartmentById(polishers.getDepartment_id());
            double diamondPrice = department.getDiamondPrice();
           
                // Calculate the total salary for the polisher based on the diamonds they completed
                long diamondsCompleted = polisherService.getDiamondsCompletedThisMonth(polisherId); // Assumed to be a method of Polisher
                double totalSalary = diamondsCompleted * diamondPrice;
                System.out.println("Polisher Name : "+polishers.getUser().getUsername()+"  Polisher salary :: "+totalSalary);
                Salary salary = new Salary(polisherId, polishers.getDepartment_id(), diamondsCompleted, totalSalary, currentDate.getMonthValue(), currentDate.getYear(), true);                
                salaryDAO.saveSalary(salary);
            
//        }
    }
}
