package com.stpl.dimonex.service;

import com.stpl.dimonex.dao.CustomerDao;
import com.stpl.dimonex.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CustomerService {
	  @Autowired
	    private CustomerDao customerDao;

	    @Transactional
	    public Customer saveCustomer(Customer customer) {
	        return customerDao.saveCustomer(customer);
	    }
}
