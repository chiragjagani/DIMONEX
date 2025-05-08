<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Place Order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
            width: 400px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        input, select {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #1abc9c;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        button:hover {
            background-color: #16a085;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Place Order</h2>
        <form action="${pageContext.request.contextPath}/order/saveOrder?userId=${userId}" method="post">
            
            <h3>Customer Details</h3>
            <input type="text" name="customerName" placeholder="Customer Name" required>
            <input type="text" name="customerContact" placeholder="Contact Number" required>

            <h3>Order Details</h3>
            <input type="text" name="orderType" placeholder="Order Type" required>

            <select name="orderStatus" required>
                <option value="">Select Order Status</option>
                <option value="Not Started">Not Started</option>
                <option value="Working">Working</option>
                <option value="Finished">Finished</option>
            </select>
            
            <input type="number" name="quantity" placeholder="Quantity" required>
            <input type="text" name="price" placeholder="Price" required>
            <input type="date" name="orderDate" required>
            
            <label>Assign Manager:</label>
   <select name="managerId" required>
    <c:forEach var="manager" items="${managers}">
        <option value="${manager.id}">${manager.user.username}</option>
    </c:forEach>
</select>

            <button type="submit">Place Order</button>
        </form>
    </div>
</body>
</html>
