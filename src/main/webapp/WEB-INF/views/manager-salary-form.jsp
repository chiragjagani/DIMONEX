<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Process Manager Salary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background-color: #f2f2f2;
        }

        h2 {
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 25px;
            border-radius: 10px;
            max-width: 500px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        input[type="submit"], .back-button {
            background-color: #28a745;
            color: white;
            padding: 10px 18px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            margin-right: 10px;
        }

        .back-button {
            background-color: #007bff;
            text-decoration: none;
            display: inline-block;
        }

        input[type="submit"]:hover, .back-button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <h2>Process Manager Salary</h2>

    <form action="${pageContext.request.contextPath}/admin/add" method="post" onsubmit="return validateForm()">
        <!-- Manager Dropdown -->
        <label for="managerId">Select Manager:</label>
        <select name="managerId" id="managerId" required>
            <c:forEach var="manager" items="${managers}">
                <option value="${manager.id}">
                    ${manager.user.username} (${manager.user.email})
                </option>
            </c:forEach>
        </select>

        <!-- Month -->
        <label for="month">Month (1-12):</label>
        <select name="month" id="month" required>
            <c:forEach var="i" begin="1" end="12">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>

        <!-- Year -->
        <label for="year">Year:</label>
        <input type="number" name="year" id="year" value="${currentYear}" min="2000" max="2099" required />

        <!-- Salary Amount -->
        <label for="amount">Salary Amount (Rs.):</label>
        <input type="number" name="amount" id="amount" min="1000" step="0.01" required />

        <input type="submit" value="Process Salary" />
        <a href="${pageContext.request.contextPath}/adminDashboard" class="back-button">Go Back</a>
    </form>
    <a href="${pageContext.request.contextPath}/admin/manager-salary-logs" class="back-button" style="background-color: #6c757d;">View Salary Logs</a>
    

    <script>
        function validateForm() {
            const year = document.getElementById("year").value;
            const amount = document.getElementById("amount").value;

            if (year < 2000 || year > 2099) {
                alert("Please enter a valid year between 2000 and 2099.");
                return false;
            }

            if (amount <= 0) {
                alert("Please enter a valid positive amount.");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
