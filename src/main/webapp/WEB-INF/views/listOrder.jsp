<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Order List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
            width: 90%;
            margin: auto;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #1abc9c;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2980b9;
        }
        .back-btn {
            margin-top: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order List</h2>
        <table>
            <tr>
                <th>Customer Name</th>
                <th>Contact</th>
                <th>Order Type</th>
                <th>Status</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Order Date</th>
                <th>Manager</th> <!-- Display Manager (if needed) -->
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.customer.name}</td>
                    <td>${order.customer.contactInfo}</td>
                    <td>${order.type}</td>
                    <td>${order.status}</td>
                    <td>${order.quantity}</td>
                    <td>Rs. ${order.amount}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.manager.user.username}</td> <!-- Display Manager Name -->
                      <td>
            <a href="${pageContext.request.contextPath}/order/editOrder?orderId=${order.id}&userId=${userId}">
                <button style="background-color: #f39c12;">Edit</button>
            </a>
        </td>
         <td>
                <a href="${pageContext.request.contextPath}/order/downloadInvoice?orderId=${order.id}" class="btn btn-primary">Download Invoice</a>
            </td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/order/monthlyChart?userId=${userId}">
    <button style="background-color: #2ecc71;">View Monthly Orders Chart</button>
</a>
        
        <a href="${pageContext.request.contextPath}/admin/dashboard?userId=${userId}" class="back-btn">
            <button>Back to Dashboard</button>
        </a>
    </div>
</body>
</html>
