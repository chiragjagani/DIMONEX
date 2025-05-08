package com.stpl.dimonex.dao;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.stpl.dimonex.model.Customer;

@Repository
public class CustomerDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    // Save or update a customer
    @Transactional
    public Customer saveCustomer(Customer customer) {
         hibernateTemplate.saveOrUpdate(customer);
         return hibernateTemplate.get(Customer.class, customer.getId());
    }

    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        return hibernateTemplate.get(Customer.class, id);
    }

    // Get all customers
    @Transactional
    public List<Customer> getAllCustomers() {
        return (List<Customer>) hibernateTemplate.find("from Customer");
    }

    // Delete a customer by ID
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = hibernateTemplate.get(Customer.class, id);
        if (customer != null) {
            hibernateTemplate.delete(customer);
        }
    }
}

