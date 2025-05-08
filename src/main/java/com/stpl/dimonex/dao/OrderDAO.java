package com.stpl.dimonex.dao;

import java.util.List;


import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.model.Order;

@Repository
public class OrderDAO {
	
	  @Autowired
	  private HibernateTemplate hibernateTemplate;
	
	// Save or Update an Order
    @Transactional
    public void saveOrUpdateOrder(Order order) {
        hibernateTemplate.saveOrUpdate(order);
    }

    // Get Order by ID
    public Order getOrderById(Long orderId) {
        return hibernateTemplate.get(Order.class, orderId);
    }

    // Get All Orders with Customer Details (Using HQL)
    @Transactional
    public List<Order> getAllOrders() {
        String hql = "SELECT o FROM Order o JOIN FETCH o.customer";
        return (List<Order>) hibernateTemplate.find(hql);
    }

    // Get Orders by Customer ID
    @Transactional
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return (List<Order>) hibernateTemplate.find("from Order where customer.id = ?", customerId);
    }

    // Update Order Status
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        hibernateTemplate.execute(session -> {
            String hql = "UPDATE Order o SET o.status = :status WHERE o.id = :orderId";
            Query query = session.createQuery(hql); // Removed `<?>`
            query.setParameter("status", status);
            query.setParameter("orderId", orderId);
            return query.executeUpdate(); // No need for `<?>`
        });
    }

    // Delete Order by ID
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = hibernateTemplate.get(Order.class, orderId);
        if (order != null) {
            hibernateTemplate.delete(order);
        }
    }
    @Transactional
    public double getTotalOrderAmountByMonthAndYear(int month, int year) {
        return hibernateTemplate.execute(session -> {
            String hql = "SELECT SUM(o.amount) FROM Order o " +
                         "WHERE FUNCTION('MONTH', FUNCTION('STR_TO_DATE', o.orderDate, '%Y-%m-%d')) = :month " +
                         "AND FUNCTION('YEAR', FUNCTION('STR_TO_DATE', o.orderDate, '%Y-%m-%d')) = :year";
            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("month", month);
            query.setParameter("year", year);
            Double result = query.uniqueResult();
            return result != null ? result : 0.0;
        });
    }

}
