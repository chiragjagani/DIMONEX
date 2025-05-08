<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company Monthly Expenses</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 40px;
            background-color: #f4f4f9;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 18px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        #expenseChart {
            max-width: 1000px;
            margin: 40px auto;
        }
    </style>
</head>
<body>

<h2>Company Monthly Expenses</h2>

<canvas id="expenseChart" height="100"></canvas>

<table>
    <tr>
        <th>Month</th>
        <th>Year</th>
        <th>Salary Expense</th>
        <th>Equipment Expense</th>
        <th>Total Expense</th>
    </tr>
    <c:forEach var="expense" items="${expenses}">
        <tr>
            <td>${expense.month}</td>
            <td>${expense.year}</td>
            <td>₹${expense.salaryExpense}</td>
            <td>₹${expense.equipmentExpense}</td>
            <td>₹${expense.salaryExpense + expense.equipmentExpense}</td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center; margin-top: 20px;">
    <form action="monthly-expense-graph" method="get">
        <button style="
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;">
            View Monthly Graph Only
        </button>
    </form>
</div>


<script>
    const labels = [];
    const salaryData = [];
    const equipmentData = [];
    const totalData = [];

    <c:forEach var="expense" items="${expenses}">
        labels.push("${expense.month}/${expense.year}");
        salaryData.push(${expense.salaryExpense});
        equipmentData.push(${expense.equipmentExpense});
        totalData.push(${expense.salaryExpense + expense.equipmentExpense});
    </c:forEach>

    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Salary Expense',
                backgroundColor: '#007bff',
                data: salaryData
            },
            {
                label: 'Equipment Expense',
                backgroundColor: '#ffc107',
                data: equipmentData
            },
            {
                label: 'Total Expense',
                backgroundColor: '#28a745',
                data: totalData
            }
        ]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Monthly Expense Summary',
                    font: {
                        size: 18
                    }
                },
                legend: {
                    position: 'top'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            return '₹' + value;
                        }
                    }
                }
            }
        }
    };

    new Chart(document.getElementById('expenseChart'), config);
</script>

</body>
</html>
