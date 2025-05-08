<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Order</title>
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
            width: 50%;
            margin: auto;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Order</h2>
        <form action="${pageContext.request.contextPath}/order/updateOrder" method="post">
            <input type="hidden" name="orderId" value="${order.id}" />
            <input type="hidden" name="userId" value="${userId}" />

            <label>Customer Name:</label>
            <input type="text" name="customerName" value="${order.customer.name}" required readonly />

            <label>Contact Info:</label>
            <input type="text" name="customerContact" value="${order.customer.contactInfo}" required readonly />

            <label>Order Type:</label>
            <select name="orderType">
                <option value="Wholesale" ${order.type == 'Wholesale' ? 'selected' : ''}>Wholesale</option>
                <option value="Retail" ${order.type == 'Retail' ? 'selected' : ''}>Retail</option>
            </select>

            <label>Status:</label>
            <select name="orderStatus">
                <option value="Not Started" ${order.status == 'Not Started' ? 'selected' : ''}>Not Started</option>
                <option value="Working" ${order.status == 'Working' ? 'selected' : ''}>Working</option>
                <option value="Finished" ${order.status == 'Finished' ? 'selected' : ''}>Finished</option>
            </select>

            <label>Quantity:</label>
            <input type="number" name="quantity" value="${order.quantity}" required />

            <label>Price:</label>
            <input type="text" name="amount" value="${order.amount}" required />

            <label>Order Date:</label>
            <input type="date" name="orderDate" value="${order.orderDate}" required />

            <label>Assign Manager:</label>
            <select name="managerId">
                <c:forEach var="manager" items="${managers}">
                    <option value="${manager.id}" ${manager.id == order.manager.id ? 'selected' : ''}>
                        ${manager.user.username} (${manager.user.email})
                    </option>
                </c:forEach>
            </select>

            <button type="submit">Update Order</button>
        </form>
    </div>
</body>
</html>
