<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Manager Salary Logs</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background-color: #f8f9fa;
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
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #343a40;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .back-btn {
            background-color: #007bff;
            color: white;
            padding: 10px 18px;
            border: none;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 5px;
        }

        .back-btn:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <h2>Manager Salary Logs</h2>

    <a href="${pageContext.request.contextPath}/adminDashboard" class="back-btn">Back to Admin Dashboard</a>

    <table>
        <tr>
            <th>Manager Name</th>
            <th>Email</th>
            <th>Month</th>
            <th>Year</th>
            <th>Amount (Rs.)</th>
            <th>Paid</th>
            <th>Payment Date</th>
        </tr>

        <c:forEach var="salary" items="${salaryLogs}">
            <tr>
                <td>${salary.manager.user.username}</td>
                <td>${salary.manager.user.email}</td>
                <td>${salary.month}</td>
                <td>${salary.year}</td>
                <td>${salary.amount}</td>
                <td>
                    <c:choose>
                        <c:when test="${salary.paid}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:out value="${salary.paymentDate}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
