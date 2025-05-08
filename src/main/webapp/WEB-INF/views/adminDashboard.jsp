<%@page import="com.stpl.dimonex.model.Admin"%>
<%@page import="com.stpl.dimonex.model.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.stpl.dimonex.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.stpl.dimonex.model.Attendance"%>
<%@page import="com.stpl.dimonex.model.Department"%>
<%@page import="com.stpl.dimonex.model.Polisher"%>
<%@page import="com.stpl.dimonex.model.Equipment"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .container-fluid {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 250px;
            background: #2c3e50;
            color: white;
            padding: 20px;
            min-height: 100vh;
        }
        .sidebar h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 22px;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 12px;
            margin: 8px 0;
            background: #34495e;
            border-radius: 5px;
            text-align: center;
            transition: 0.3s;
        }
        .sidebar a:hover {
            background: #1abc9c;
        }
        .content {
            flex-grow: 1;
            padding: 30px;
            background: white;
            overflow-y: auto;
        }
        h2 {
            color: #2c3e50;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            background: white;
            box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background: #1abc9c;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background: #f2f2f2;
        }
        tr:hover {
            background: #e9f5f2;
        }
        .btn-custom {
            padding: 8px 12px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-add {
            background: #28a745;
            color: white;
        }
        .btn-add:hover {
            background: #218838;
        }
        form {
            display: inline;
        }
        input, select {
            padding: 8px;
            margin: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- Sidebar -->
   <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="#managers">👤 Managers</a>
        <a href="#polishers">🔹 Polishers</a>
        <a href="#addManager">➕ Add Manager</a>
        <a href="${pageContext.request.contextPath}/order/listOrder?userId=${userId}">📜 List Orders</a>
        <a href="${pageContext.request.contextPath}/order/placeOrder?userId=${userId}">🛒 Place Order</a>
        
        
           <a href="${pageContext.request.contextPath}/admin/add">💰 Salary</a>
         <a href="${pageContext.request.contextPath}/admin/expenses">💰 Expense</a>
         <a href="${pageContext.request.contextPath}/admin/monthly-profit-expense-graph">💰 Profit</a>   
        <a href="CreateEquipment.jsp">Create Equipment</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h2>Welcome, Admin</h2>

        <!-- Manager List -->
        <h3 id="managers">👤 Manager List</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Expenses</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="manager" items="${managers}">
                <tr>
                    <td>${manager.user.id}</td>
                    <td>${manager.user.username}</td>
                    <td>${manager.user.email}</td>
                    <td>${manager.expenses}</td>
                    <td>
                        <form action="editManager" method="get">
                            <input type="hidden" name="id" value="${manager.user.id}" />
                            <button type="submit" class="btn-custom btn-edit">✏ Edit</button>
                        </form>
                        <form action="deleteManager" method="post">
                            <input type="hidden" name="id" value="${manager.user.id}" />
                            <input type="hidden" name="userId" value="${userId}" />
                            <button type="submit" class="btn-custom btn-delete" onclick="return confirm('Are you sure?')">🗑 Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <!-- Add Manager -->
        <h3 id="${pageContext.request.contextPath}/addManager">➕ Add New Manager</h3>
        <form action="addManager" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="number" step="0.01" name="expenses" placeholder="Expenses" required>
            <input type="hidden" name="userId" value=${userId} />
            <button type="submit" class="btn-custom btn-add">Add Manager</button>
        </form>

        <!-- Polisher List -->
        <h3 id="polishers">🔹 Polisher List</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Skill Level</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="polisher" items="${polishers}">
                <tr>
                    <td>${polisher.user.id}</td>
                    <td>${polisher.user.username}</td>
                    <td>${polisher.user.email}</td>
                    <td>${polisher.skillLevel}</td>
                    <td>
                        <form action="editPolisher" method="get">
                            <input type="hidden" name="id" value="${polisher.user.id}" />
                            <button type="submit" class="btn-custom btn-edit">✏ Edit</button>
                        </form>
                        <form action="deletePolisher" method="post">
                            <input type="hidden" name="id" value="${polisher.user.id}" />
                            <input type="hidden" name="userId" value="${userId}" />
                            <button type="submit" class="btn-custom btn-delete" onclick="return confirm('Are you sure?')">🗑 Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- Main Content -->
    <div class="content">
        <h2>Welcome, Admin</h2>

        <!-- Create Equipment -->
        <h3 id="createEquipment">🔧 Create Equipment</h3>
        <form id="addEquipmentForm">
            <input type="text" id="equipmentName" placeholder="Equipment Name" required>
            <input type="text" id="equipmentType" placeholder="Equipment Type" required>
            <select id="equipmentStatus">
                <option value="true">Available</option>
                <option value="false">Not Available</option>
            </select>
            <button type="button" class="btn-custom btn-add" onclick="addEquipment()">Add Equipment</button>
        </form>

    <div class="container mt-4">
    <h2>🔧 Equipment List</h2>

    <table class="table table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Availability</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty equipment}">
                    <c:forEach var="eqs" items="${equipment}">
                        <tr>
                            <td>${eqs.id}</td>
                            <td>${eqs.name}</td>
                            <td>${eqs.type}</td>
                            <td>${eqs.availabilityStatus eq 'AVAILABLE' ? '✅ Yes' : '❌ No'}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="4" class="text-center text-danger">No Equipment Available</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>
    
</div>

    <div id="attendanceForPolishers" class="dashboard-section">
			<h2>Attendance For Polishers</h2>

			
			<form action="/dimonex/attendance/attsubmitadmin" method="POST">
			<div>
				<label for="attendanceDate">Attendance Date:</label> <input
					type="date" id="attendanceDate" name="attendanceDate"
					value="<%=request.getAttribute("attendanceDate")%>"
					onchange="fetchAttendanceData()" />
			</div>
				<table>
					<tr>
						<th>User Name</th>
						<th>Action</th>
					</tr>
					<% Admin admin=(Admin)request.getAttribute("admin"); %>
					<%
					List<Manager> users = (List<Manager>) request.getAttribute("managers");
					if (users != null) {
						for (Manager user : users) {
					%>
					<tr>
						<td><%=user.getUser().getUsername()%></td>
						<td>
							<%-- <button type="submit"
							name="attendanceStatus_<%=user.getUser().getId()%>"
							value="Present" class="present">Present</button>
						<button type="submit"
							name="attendanceStatus_<%=user.getUser().getId()%>"
							value="Absent" class="absent">Absent</button> <input
						type="hidden" name="userId_<%=user.getUser().getId()%>"
						value="<%=user.getId()%>" /> --%> <input type="radio"
							name="attendanceStatus_<%=user.getUser().getId()%>" value="Present">
							Present</input> <input type="radio"
							name="attendanceStatus_<%=user.getUser().getId()%>" value="Absent">
							Absent</input>  <input type="hidden" name="userId_<%=user.getId()%>"
							value="<%=user.getId()%>" /><%-- <% Manager managerss = (Manager) request.getAttribute("manager");
 %>--%> <input type="hidden" name="admin" value="<%=admin.getId()%>" /> 
						</td>
					</tr>
					<%
					}
					}
					%>
				</table>
				 <button type="submit">Submit Attendance</button>
			</form>
		</div>

    




<script>
        // This function will fetch attendance data for the selected date using AJAX
        function fetchAttendanceData() {
            var selectedDate = document.getElementById('attendanceDate').value;
            
            // Send an AJAX request to the server to fetch attendance data for the selected date
            $.ajax({
                url: "<%= request.getContextPath() %>/attendance/attendance-data",
                method: "GET",
                data: { attendanceDate: selectedDate },
                success: function(data) {
                	$("input[name^='attendanceStatus_']").prop('checked', false);
                    // Iterate over the returned attendance data and pre-select the radio buttons
                   console.log(data.length==0)
                  if(data.length!=0){
                    data.forEach(function(attendance) {
                    	  
                        var userId = attendance.userId;
                        var status = attendance.status;
                        var presentRadio = $("input[name='attendanceStatus_" + userId + "'][value='Present']");
                        var absentRadio = $("input[name='attendanceStatus_" + userId + "'][value='Absent']");
                        
                        // Check the radio button based on the status
                        if (status === "Present") {
                            presentRadio.prop('checked', true);
                        } else if (status === "Absent") {
                            absentRadio.prop('checked', true);
                        }else{
                        	 presentRadio.prop('checked', false);
                             absentRadio.prop('checked', false);

                        }
                    });
                  }else {
                	  $("input[name^='attendanceStatus_']").prop('checked', false);
         
                  }
                }
            });
        }

        // Trigger initial fetch on page load (for today by default)
        $(document).ready(function() {
            fetchAttendanceData();
        });
        
        
        
        
        
        function addEquipment() {
            let name = $("#equipmentName").val();
            let type = $("#equipmentType").val();
            let availableStatus = $("#equipmentStatus").val();
        	console.log(name)
        	console.log(type)
            if (!name || !type) {
                alert("Please fill all fields!");
                return;
            }

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/api/equipment/add",
                contentType: "application/json",  
                data: JSON.stringify({
                    name: name,
                    type: type,
                    availableStatus: availableStatus === "true"
                }),
                success: function(response) {
                    alert(response);
                    location.reload();  // Refresh page to update equipment list
                },
                error: function(xhr, status, error) {
                    alert("Error adding equipment: " + xhr.responseText);
                }
            });
        }


    </script>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
