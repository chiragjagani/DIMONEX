<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Monthly Orders Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial;
            padding: 20px;
            background-color: #f7f7f7;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 8px rgba(0,0,0,0.2);
            width: 80%;
            margin: auto;
        }
        canvas {
            max-width: 100%;
        }
        button {
            background-color: #3498db;
            color: white;
            padding: 10px 15px;
            margin-top: 20px;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 style="text-align:center;">Monthly Orders Overview</h2>
        <canvas id="orderChart"></canvas>

        <a href="${pageContext.request.contextPath}/admin/dashboard?userId=${userId}">
            <button>Back to Dashboard</button>
        </a>
    </div>

    <script>
        const ctx = document.getElementById('orderChart').getContext('2d');

        const chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [<c:forEach items="${months}" var="month">'${month}',</c:forEach>],
                datasets: [{
                    label: 'Orders Per Month',
                    data: [<c:forEach items="${counts}" var="count">${count},</c:forEach>],
                    backgroundColor: 'rgba(46, 204, 113, 0.7)',
                    borderColor: 'rgba(39, 174, 96, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });
    </script>
</body>
</html>
