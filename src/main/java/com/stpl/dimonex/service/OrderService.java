package com.stpl.dimonex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stpl.dimonex.dao.OrderDAO;
import com.stpl.dimonex.model.Order;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    // Save or Update an Order
    @Transactional
    public void saveOrUpdateOrder(Order order) {
        orderDAO.saveOrUpdateOrder(order);
    }

    // Get Order by ID
    public Order getOrderById(Long orderId) {
        return orderDAO.getOrderById(orderId);
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    // Get Orders by Customer ID
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderDAO.getOrdersByCustomerId(customerId);
    }

    // Update Order Status
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

    // Delete Order by ID
    @Transactional
    public void deleteOrder(Long orderId) {
        orderDAO.deleteOrder(orderId);
    }
    
    
    public double getTotalOrderAmountByMonthAndYear(int month, int year) {
        return orderDAO.getTotalOrderAmountByMonthAndYear(month, year);
    }

}
