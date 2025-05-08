<!--   <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Dashboard</title><link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    Bootstrap CSS (For responsiveness and components)

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min .css"
	rel="stylesheet">
    
    
    <link rel="stylesheet" href="styles.css"> 
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
        }
        .sidebar {
            width: 250px;
            background: #2c3e50;
            color: white;
            padding: 20px;
            height: 100vh;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
        }
        .sidebar a:hover {
            background: #34495e;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        .dashboard-section {
            background: #f4f4f4;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background: #3498db;
            color: white;
        }
        .btn {
            padding: 8px 12px;
            background: #27ae60;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Manager Dashboard</h2>
        <a href="#polishers">Manage Polishers</a>
        <a href="#attendance">Attendance</a>
        <a href="#tasks">Assign Tasks</a>
        <a href="#equipment">Equipment Usage</a>
    </div>
    <div class="content">
        <div id="polishers" class="dashboard-section">
            <h2>Polishers Under Management</h2>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Skill Level</th>
                    <th>Experience</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                <tr>
                    <td>John Doe</td>
                    <td>Expert</td>
                    <td>5 years</td>
                    <td>Active</td>
                    <td><button class="btn">Update</button></td>
                </tr>
                <tr>
                    <td>Jane Smith</td>
                    <td>Intermediate</td>
                    <td>3 years</td>
                    <td>Inactive</td>
                    <td><button class="btn">Update</button></td>
                </tr>
            </table>
        </div>
        
        <div id="attendance" class="dashboard-section">
            <h2>Attendance Management</h2>
            <label>Select Polisher:</label>
            <select>
                <option>John Doe</option>
                <option>Jane Smith</option>
            </select>
            <button class="btn">Mark Present</button>
        </div>
        
        <div id="tasks" class="dashboard-section">
            <h2>Assign New Task</h2>
            <label>Select Polisher:</label>
            <select>
                <option>John Doe</option>
                <option>Jane Smith</option>
            </select>
            <input type="text" placeholder="Task Details">
            <button class="btn">Assign Task</button>
        </div>
        
        <div id="equipment" class="dashboard-section">
            <h2>Equipment Usage</h2>
            <table>
                <tr>
                    <th>Equipment</th>
                    <th>Assigned To</th>
                    <th>Usage Time</th>
                </tr>
                <tr>
                    <td>Polishing Machine</td>
                    <td>John Doe</td>
                    <td>3 hours</td>
                </tr>
                <tr>
                    <td>Grinding Tool</td>
                    <td>Jane Smith</td>
                    <td>2 hours</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>

 
 	 -->


<!DOCTYPE html>
<%@page import="java.time.LocalDate"%>
<%@page import="com.stpl.dimonex.model.Salary"%>
<%@page import="com.stpl.dimonex.model.Task"%>
<%@page import="com.stpl.dimonex.model.Department"%>
<%@page import="com.stpl.dimonex.model.Polisher"%>
<%@page import="java.util.List"%>
<%@page import="com.stpl.dimonex.model.Manager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manager Dashboard</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap JS and dependencies -->
	<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.js"></script>
<!--     Bootstrap CSS (For responsiveness and components)
 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min .css"
	rel="stylesheet">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	background-color: #f4f4f4;
}

/* Sidebar Styling */
.sidebar {
	width: 250px;
	background: #2c3e50;
	color: white;
	padding: 20px;
	position: fixed;
	height: 100vh;
	overflow-y: auto;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.sidebar .profile {
	text-align: center;
	margin-bottom: 20px;
}

.sidebar .profile img {
	width: 80px;
	height: 80px;
	border-radius: 50%;
	margin-bottom: 10px;
}

.sidebar a {
	display: block;
	color: white;
	text-decoration: none;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 10px;
	text-align: center;
	transition: 0.3s;
}

.sidebar a:hover {
	background: #34495e;
}

/* Content Section */
.content {
	margin-left: 250px;
	flex: 1;
	padding: 20px;
}

.dashboard-section {
	background: white;
	padding: 20px;
	border-radius: 8px;
	margin-bottom: 20px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background: #3498db;
	color: white;
}

.btn-custom {
	background: #27ae60;
	color: white;
	border: none;
	padding: 8px 12px;
	border-radius: 5px;
	cursor: pointer;
}

#calendar {
	max-height: 500px;
}
/* Responsive Design */
@media screen and (max-width: 768px) {
	.sidebar {
		width: 200px;
	}
	.content {
		margin-left: 200px;
	}
}

@media screen and (max-width: 576px) {
	.sidebar {
		position: relative;
		width: 100%;
		height: auto;
	}
	.content {
		margin-left: 0;
	}
}
</style>
</head>
<body>

	<!-- Sidebar -->
	<div class="sidebar">
		<div>
			<div class="profile">
				<img src="https://static8.depositphotos.com/1468291/934/i/450/depositphotos_9346925-stock-photo-portrait-of-office-worker-at.jpg" alt="Manager Profile">
				<h4>Manager Name</h4>
				<p>Manager</p>
			</div>
			<a href="#polishers">Manage Polishers</a> <a href="#attendance">Attendance</a>
			<a href="#tasks">Assign Tasks</a> <a href="#approval-status">Approval
				Status</a> <a href="equipment">Equipment Usage</a> <a
				href="#attendanceForPolishers">Attendance For Polishers</a> <a
				href="#task-assign-to-polisher">Task Assign To Polishers</a>  <a
				href="listOrder/${id}">Orders</a>
		</div>
	</div>

	<!-- Main Content -->
	<div class="content">

		<section id="profile-section" class="dashboard-section">
			<div class="card">
				<div class="card-header">
					<h5>My Profile</h5>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-4">
							<p>
								<strong>Name:</strong>Parth
							</p>
							<p>
								<strong>Skill Level:</strong>good
							</p>
							<p>
								<strong>Experience Level:</strong>10 Years
							</p>
							<p>
								<strong>Department:</strong> Polishing - Granding
							</p>
						</div>
						<div class="col-md-4">
							<p>
								<strong>Status:</strong>Availabe
							</p>



							<p>
								<strong>Manager:</strong> Parth
							</p>
							<p>
								<strong>Assigned Since:</strong> 2020
							</p>
						</div>
					</div>
				</div>
				<button class="btn btn-primary mt-2" data-toggle="modal"
					data-target="#editPolisherModal">Edit</button>
			</div>
		</section>



		<div id="polishers" class="dashboard-section">
			<h2>Polishers Under Management</h2>
			<table>
				<tr>
					<th>Name</th>
					<th>Skill Level</th>
					<th>Experience</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>

				<%
				List<Polisher> allPolishers = (List<Polisher>) request.getAttribute("polisher");
				for (Polisher polisher : allPolishers) {
				%>
				<tr>
					<td><%=polisher.getUser().getUsername()%></td>
					<td><%=polisher.getSkillLevel()%></td>
					<td><%=polisher.getExperienceLevel()%></td>
					<td><%=polisher.getAvailableStatus()%></td>
					<td>
						<button class="btn-custom">Update</button>
					</td>
				</tr>
				<%
				}
				%>
			</table>
		</div>












			<!-- Bootstrap & jQuery Scripts -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<div id="polishers" class="dashboard-section">
		<section id="attendance-section">
			<div class="card">
				<div class="card-header">
					<h5>Attendance</h5>
				</div>
				<div class="card-body">
					<div class="container mt-5">

						<div id='calendar'></div>
					</div>
				</div>
			</div>
		</section>
</div>














		<div id="tasks" class="dashboard-section">
			<h2>Assign New Task</h2>



			<form action="/dimonex/tasks/save" method="post">
				<div class="form-group">
					<label for="taskName">Task Name:</label> <input type="text"
						id="taskName" name="taskName" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="description">Description:</label>
					<textarea id="description" name="description" class="form-control"></textarea>
				</div>

				<div class="form-group">
					<label for="dimonds">No of Dimonds:</label> <input id="dimonds"
						name="dimonds" class="form-control" />
				</div>
				<div class="form-group">
					<label for="deadline">Deadline:</label> <input type="date"
						id="deadline" name="deadline" class="form-control" required>
				</div>
				<div class="form-group">
					<label for="track">Track:</label> <input type="text" id="track"
						name="track" class="form-control">
				</div>
				<div class="form-group">
					<label for="manager">Assign Manager:</label> <select id="manager"
						name="managerId" class="form-control" required>
						<%
						Manager managers = (Manager) request.getAttribute("manager");
						%>
						<option value="<%=managers.getId()%>"><%=managers.getUser().getUsername()%></option>

					</select>
				</div>


				<div class="form-group">
					<label for="polisher">Assign Polisher:</label> <select
						id="polisher" name="polisherId" class="form-control" required>
						<%
						List<Polisher> polishers = (List<Polisher>) request.getAttribute("polisher");
						%>
						<%
						for (Polisher polisher : polishers) {
						%>
						<option value="<%=polisher.getId()%>"><%=polisher.getUser().getUsername()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
				<div class="form-group">
					<label for="department">Select Department:</label> <select
						id="department" name="departmentId" class="form-control" required>
						<%
						Department departments = (Department) request.getAttribute("department");
						%>

						<option value="<%=departments.getId()%>"><%=departments.getName()%>
						</option>

					</select>
				</div>
				<button type="submit" class="btn btn-success mt-2">Save
					Task</button>
			</form>
		</div>
</section>
		<div id="task-assign-to-polisher" class="dashboard-section">
			<h2></h2>
			<table>
				<tr>
					<th>Task Name</th>
					<th>Description</th>
					<th>No of Dimonds</th>
					<th>Deadline</th>
					<th>Track</th>
				</tr>
				<%
				List<Task> AllTasks = (List<Task>) request.getAttribute("tasks");
				%>
				<%
				for (Task task : AllTasks) {
				%>
				<tr>
					<td><%=task.getTaskName()%></td>
					<td><%=task.getDescription()%></td>
					<td><%=task.getNumberOfDiamonds()%></td>
					<td><%=task.getDeadline()%></td>
					<td><%=task.getTrack()%></td>

				</tr>
				<%
				}
				%>


			</table>
		</div>





		<div id="approval-status" class="dashboard-section">
			<h2>Request Extention Status</h2>
			<table>
				<tr>
					<th>Task Name</th>
					<th>Current Deadline</th>
					<th>Requested Extension</th>
					<th>Polisher</th>
					<th>Action</th>
				</tr>
				<%
				List<Task> extensionRequests = (List<Task>) request.getAttribute("extensionRequests");
				for (Task task : extensionRequests) {
				%>
				<tr>
					<td><%=task.getTaskName()%></td>
					<td><%=task.getDeadline()%></td>
					<td><%=task.getRequestedExtensionDeadline()%></td>
					<td><%=task.getPolisher().getUser().getUsername()%></td>
					<td>
						<form action="/dimonex/manager/approveExtension" method="post">
							<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
							<button type="submit" name="decision" value="approved"
								class="btn btn-success">Approve</button>
							<button type="submit" name="decision" value="rejected"
								class="btn btn-danger">Reject</button>
						</form>
					</td>
				</tr>
				<%
				}
				%>

			</table>
		</div>

		<div id="attendanceForPolishers" class="dashboard-section">
			<h2>Attendance For Polishers</h2>

			
			<form action="/dimonex/attendance/attsubmit" method="POST">
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
					<%
					List<Polisher> users = (List<Polisher>) request.getAttribute("polisher");
					if (users != null) {
						for (Polisher user : users) {
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
							Absent</input> <input type="hidden" name="userId_<%=user.getId()%>"
							value="<%=user.getId()%>" /> <%
 Manager managerss = (Manager) request.getAttribute("manager");
 %> <input type="hidden" name="manager" value="<%=managerss.getId()%>" />
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
    </script>













<%--  <% 
            Boolean isLastFiveDays = (Boolean) request.getAttribute("isLastFiveDays");
            if (isLastFiveDays != null && isLastFiveDays) {
        %> --%>

<div id="equipment" class="dashboard-section">

 <div class="container">
        <h3 class="mt-3">Salary For Polishers</h3>
        
        <!-- Polishers List -->
        <div class="card mt-4">
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Polisher ID</th>
                            <th>Name</th>
                            <th>Skill Level</th>
                            <th>Experience Level</th>
                            <th>Status</th>
                            <th>Pay Salary</th> 
                        </tr> 
                    </thead>
                    <tbody>
                        <% 
                           
                        List<Polisher> polishers2 = (List<Polisher>) request.getAttribute("polisher");
                        List<Salary> salaryAll = (List<Salary>) request.getAttribute("salaryAll");
                            for (Polisher polisher : polishers2) {
               	             System.out.println("Salary all inside For loop ........"+salaryAll);

                            	 boolean isUnpaid = true;
                            	    if(salaryAll.isEmpty()==true){
                            	    	isUnpaid=true;
                            	    }
                            	    // Check if the polisher has an unpaid salary
                            	    for (Salary salary : salaryAll) {
                            	        if (salary.getPolisherId()==polisher.getId() && salary.getSalaryMonth()==LocalDate.now().getMonthValue()) {
                            	             System.out.println("Inside For loop ........"+polisher);
                            	        	isUnpaid = false;
                            	            
                            	            break;  
                            	          
                            	        }
                            	    }
                            	    
                            	    // Only display the polisher if they have an unpaid salary
                            	    if (isUnpaid) {
                        %>
                            <tr>
                                <td><%= polisher.getId() %></td>
                                <td><%= polisher.getUser().getUsername()%></td>
                                <td><%= polisher.getSkillLevel() %></td>
                                <td><%= polisher.getExperienceLevel() %></td>
                                <td><%= polisher.getAvailableStatus() ? "Available" : "Not Available" %></td>
                                <td>
                                    <form action="<%= request.getContextPath() %>/salary/calculate" method="POST">
                                        <input type="hidden" name="polisherId" value="<%= polisher.getId() %>" />
                                         <input type="hidden" name="managerId" value="<%= managers.getId() %>" />
                                        <button type="submit" class="btn btn-success pay-button">Pay Salary</button>
                                    </form>
                                </td>
                            </tr>
                        <% }} %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Show Salary Payment History Only If It's The Last 5 Days of the Month -->
        <%-- <% 
            Boolean isLastFiveDays = (Boolean) request.getAttribute("isLastFiveDays");
            if (isLastFiveDays != null && isLastFiveDays) {
        %> --%>
         <!--    <h3 class="mt-4">Salary Payment History (Last 5 Days)</h3>
            <div class="card salary-table">
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Polisher ID</th>
                                <th>Name</th>
                                <th>Amount</th>
                                <th>Paid On</th>
                            </tr>
                        </thead> -->
                       <%--  <tbody>
                            <% 
                                List<SalaryPayment> salaryPayments = (List<SalaryPayment>) request.getAttribute("salaryPayments");
                                for (SalaryPayment payment : salaryPayments) {
                            %>
                                <tr>
                                    <td><%= payment.getPolisher().getId() %></td>
                                    <td><%= payment.getPolisher().getUser().getName() %></td>
                                    <td><%= payment.getAmount() %></td>
                                    <td><%= payment.getPaymentDate() %></td>
                                </tr>
                            <% } %>
                        </tbody> --%>
                   <!--  </table>
                </div>
            </div> -->
       <%--  <% } %> --%>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</div>

<%-- <%} %> --%>

		<div id="equipment" class="dashboard-section">
			<h2>Equipment Usage</h2>
			<table>
				<tr>
					<th>Equipment</th>
					<th>Assigned To</th>
					<th>Usage Time</th>
				</tr>
				<tr>
					<td>Polishing Machine</td>
					<td>John Doe</td>
					<td>3 hours</td>
				</tr>
				<tr>
					<td>Grinding Tool</td>
					<td>Jane Smith</td>
					<td>2 hours</td>
				</tr>
			</table>
		</div>
		<%@ page import="com.stpl.dimonex.model.ManagerSalary" %>

<%
    ManagerSalary managerSalary = (ManagerSalary) request.getAttribute("managerSalary");
%>

<div>
    <h2>My Salary Details</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Month</th>
            <th>Year</th>
            <th>Salary Amount</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <tr>
            <td><%= managerSalary.getMonth() %></td>
            <td><%= managerSalary.getYear() %></td>
            <td><%= managerSalary.getAmount() %></td>
            <td><%= managerSalary.isPaid() ? "Paid" : "Unpaid" %></td>
            <td>
                <form action="${pageContext.request.contextPath}/manager/downloadSalarySlip" method="post">
                    <input type="hidden" name="managerId" value="<%= managerSalary.getManager().getId() %>"/>
                    <button type="submit">Download PDF</button>
                </form>
            </td>
        </tr>
    </table>
</div>
		

	</div>
	



<script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var today = new Date();
            var firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
            var lastDay = new Date(today.getFullYear(), today.getMonth() + 1, 0);
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                editable: false,
                selectable: false,
           <%--      events: [
                    <%List<Attendance> filteredAttendance = (List<Attendance>) request.getAttribute("attendanceList");%>
                    <% System.out.println("----------------******************************************-------------------------------------------------"+filteredAttendance); %>
                    <%for (Attendance record : filteredAttendance) {%>
                    {
                        title: '<%=record.getStatus()%>',
                        start: '<%=record.getDate()%>'
                    },
                    <%}%>
                ] --%>
            });
            calendar.render();
        });
        </script>
</body>

</html>