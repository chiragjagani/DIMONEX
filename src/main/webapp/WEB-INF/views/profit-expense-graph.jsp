<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Income & Expense Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
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

        h2 {
            text-align: center;
            margin-bottom: 30px;
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
    <h2>Income & Expense Comparison by Category (Feb–Apr 2025)</h2>

    <canvas id="monthlyCategoryChart" height="100"></canvas>

    <a href="${pageContext.request.contextPath}/admin/dashboard?userId=${userId}">
        <button>Back to Dashboard</button>
    </a>
</div>

<script>
    const labels = ["Feb 2025", "Mar 2025", "Apr 2025"];

    const data = {
        labels: labels,
        datasets: [
            // Income categories
            {
                label: 'Order',
                data: [35000, 38000, 37000],
                backgroundColor: '#66BB6A',
                stack: 'Income'
            },
            // Expense categories
            {
                label: 'Salary',
                data: [20000, 21000, 22000],
                backgroundColor: '#EF5350',
                stack: 'Expense'
            },
            {
                label: 'Equipment',
                data: [10000, 12000, 11000],
                backgroundColor: '#E57373',
                stack: 'Expense'
            },
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
                    text: 'Income & Expense Comparison (Feb–Apr 2025)',
                    font: { size: 18 }
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `${context.dataset.label}: ₹${context.parsed.y.toLocaleString()}`;
                        }
                    }
                }
            },
            scales: {
                x: {
                    stacked: true
                },
                y: {
                    stacked: true,
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            return '₹' + (value / 1000) + 'K';
                        }
                    }
                }
            }
        }
    };

    new Chart(document.getElementById('monthlyCategoryChart'), config);
</script>
<h3 style="text-align:center; margin-top: 40px;">Monthly Financial Summary</h3>

<table style="width: 100%; border-collapse: collapse; margin-top: 20px;">
    <thead>
        <tr style="background-color: #3498db; color: white;">
            <th style="padding: 10px;">Month</th>
            <th style="padding: 10px;">Total Income (₹)</th>
            <th style="padding: 10px;">Total Expense (₹)</th>
            <th style="padding: 10px;">Profit (₹)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="summary" items="${summaries}">
            <tr style="background-color: #f2f2f2;">
                <td style="padding: 10px;">${summary.month}</td>
                <td style="padding: 10px;">₹${summary.profit}</td>
                <td style="padding: 10px;">₹${summary.totalExpense}</td>
                <td style="padding: 10px; font-weight: bold; color: ${summary.profit >= 0 ? 'green' : 'red'};">
                    ₹${summary.profit}
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
