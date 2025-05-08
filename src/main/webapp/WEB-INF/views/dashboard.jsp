<%@page import="com.stpl.dimonex.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.stpl.dimonex.model.Attendance"%>
<%@page import="com.stpl.dimonex.model.Department"%>
<%@page import="com.stpl.dimonex.model.Polisher"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Polisher Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--     Bootstrap CSS (For responsiveness and components)
 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min .css"
	rel="stylesheet">

<!--     Inline Custom CSS
 -->
<style>
/* Global styles */
body {
	font-family: Arial, sans-serif;
	background-color: #f7f8fa;
}

.sidebar {
	height: 100vh;
	background-color: #343a40;
	color: white;
	position: fixed;
	width: 250px;
	top: 0;
	left: 0;
	z-index: 100;
	padding-top: 20px;
}

.sidebar .nav-item .nav-link {
	color: #ffffff;
	padding: 12px;
}

.sidebar .nav-item .nav-link.active {
	background-color: #007bff;
}

.sidebar .nav-item .nav-link:hover {
	background-color: #495057;
}

.main-content {
	margin-left: 250px;
	padding: 20px;
}

.card {
	margin-bottom: 20px;
}

.card-header {
	background-color: #007bff;
	color: white;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-secondary {
	background-color: #6c757d;
	border-color: #6c757d;
}

.table th, .table td {
	vertical-align: middle;
}

.form-group label {
	font-weight: bold;
}

/* Media query for small screens */
@media ( max-width : 768px) {
	.sidebar {
		position: relative;
		width: 100%;
		height: auto;
	}
	.main-content {
		margin-left: 0;
	}
}

#calendar {
	max-height: 500px;
}
</style>
</head>
<body>


	<%
	Polisher polisher = (Polisher) request.getAttribute("polisher");

	List<Attendance> attendance = (List<Attendance>) request.getAttribute("attendanceList");
	/* System.out.println(attendance); */
	Department departmnet = (Department) request.getAttribute("department");
	%>

	<!--     Sidebar for Polisher Navigation
 -->
	<nav class="sidebar">
		<h4 class="text-center text-white">Polisher Dashboard</h4>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active"
				href="#profile-section"> Profile </a></li>
			<li class="nav-item"><a class="nav-link"
				href="#attendance-section"> Attendance </a></li>
			<li class="nav-item"><a class="nav-link" href="#tasks-section">
					My Tasks </a></li>
			<li class="nav-item"><a class="nav-link" href="#salary-section">
					Salary </a></li>
			<li class="nav-item"><a class="nav-link"
				href="#equipment-section"> Equipment </a></li>
		</ul>
	</nav>

	<!--     Main Content Area
 -->
	<div class="main-content">
		<!--         Profile Section
 -->
		<section id="profile-section">
			<div class="card">
				<div class="card-header">
					<h5>My Profile</h5>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-4">
							<p>
								<strong>Name:</strong><%=polisher.getUser().getUsername()%></p>
							<p>
								<strong>Skill Level:</strong><%=polisher.getSkillLevel()%></p>
							<p>
								<strong>Experience Level:</strong><%=polisher.getExperienceLevel() == null ? "Not Define " : polisher.getExperienceLevel()%>years
							</p>
							<p>
								<strong>Department:</strong> Polishing -
								<%=departmnet.getName()%></p>
						</div>
						<div class="col-md-4">
							<p>
								<strong>Status:</strong><%=polisher.getAvailableStatus() ? "Active" : ""%>
							</p>

							<%
							String ss = polisher.getManager().getUser().getUsername();
							%>

							<p>
								<strong>Manager:</strong>
								<%=ss%>
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


		<div class="modal fade" id="editPolisherModal" tabindex="-1"
			role="dialog" aria-labelledby="editPolisherModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Update Polisher Details</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="/dimonex/updatePolisher" method="post">
							<input type="hidden" id="polisherId" name="polisherId"
								value="<%=polisher.getId()%>">

							<div class="form-group">
								<label for="editName">Name:</label> <input type="text"
									class="form-control" id="editName" name="name"
									value="<%=polisher.getUser().getUsername()%>" required>
							</div>

							<div class="form-group">
								<label for="editStatus">Status:</label> <select
									class="form-control" id="editStatus" name="status">
									<option value="Active">Active</option>
									<option value="Inactive">Inactive</option>
								</select>
							</div>

							<div class="form-group">
								<label for="editSkillLevel">Skill Level:</label> <select
									class="form-control" id="editSkillLevel" name="skillLevel">
									<option value="Beginner">Beginner</option>
									<option value="Intermediate">Intermediate</option>
									<option value="Expert">Expert</option>
								</select>
							</div>

							<div class="form-group">
								<label for="editExperience">Experience (Years):</label> <input
									type="number" class="form-control" id="editExperience"
									name="experience" value="<%=polisher.getExperienceLevel()%>"
									min="0" required>
							</div>

							<button type="submit" class="btn btn-success">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>


		<!-- Bootstrap & jQuery Scripts -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

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



		<%
		List<Task> assigntasks = (List<Task>) request.getAttribute("assignTasks");
		%>


 
 
<%-- <section id="tasks-section" class="mt-5">
    <div class="card">
        <div class="card-header">
            <h5>My Tasks</h5>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Task</th>
                        <th>Deadline</th>
                        <th>No. of Diamonds</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    if (assigntasks != null) {
                        for (Task task : assigntasks) {
                    %>
                    <tr>
                        <td><%= task.getTaskName() %></td>
                        <td>
                            <%
                            if (task.isExtensionRequested() && task.getRequestedExtensionDeadline() != null) {
                            %>
                            <span style="color: orange;"><%= task.getRequestedExtensionDeadline() %> (Pending Approval)</span>
                            <%
                            } else {
                            %>
                            <%= task.getDeadline() %>
                            <%
                            }
                            %>
                        </td>
                        <td><%= task.getNumberOfDiamonds() %></td>
                        <td>
                            <form action="/dimonex/tasks/updateTaskStatus" method="post">
                                <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                                <input type="hidden" name="polisherId" value="<%= task.getPolisher().getId() %>">
                                <select class="form-control" name="status" onchange="this.form.submit()">
                                    <option value="In Progress" <%= task.getTrack().equals("In Progress") ? "selected" : "" %>>In Progress</option>
                                    <option value="On Hold" <%= task.getTrack().equals("On Hold") ? "selected" : "" %>>On Hold</option>
                                    <option value="Completed" <%= task.getTrack().equals("Completed") ? "selected" : "" %>>Completed</option>
                                </select>
                            </form>
                        </td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="openExtensionModal('<%= task.getTaskId() %>')">Request Extension</button>
                        </td>
                    </tr>
                    <%
                        }
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</section>
 --%>
 
 
 
 
 
 
 <section id="tasks-section" class="mt-5">
    <div class="card">
        <div class="card-header">
            <h5>My Tasks</h5>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Task</th>
                        <th>Deadline</th>
                        <th>No. of Diamonds</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    if (assigntasks != null) {
                        for (Task task : assigntasks) {
                    %>
                     <%     if(!task.getTrack().equals("Completed")){ %>
                    <tr>
                    
                        <td><%= task.getTaskName() %></td>
                        <td>
                            <%
                            if (task.isExtensionRequested() && task.getRequestedExtensionDeadline() != null) {
                            %>
                            <span style="color: orange;"><%= task.getRequestedExtensionDeadline() %> (Pending Approval)</span>
                            <%
                            } else {
                            %>
                            <%= task.getDeadline() %>
                            <%
                            }
                            %>
                        </td>
                        <td><%= task.getNumberOfDiamonds() %></td>
                        <td>
                            <form action="/dimonex/tasks/updateTaskStatus" method="post">
                           
                                <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                                <input type="hidden" name="polisherId" value="<%= task.getPolisher().getId() %>">
                                <select class="form-control" name="status" onchange="this.form.submit()" 
                                        <%= task.getTrack().equals("Completed") ? "disabled" : "" %>> <!-- Disable if completed -->
                                    <option value="In Progress" <%= task.getTrack().equals("In Progress") ? "selected" : "" %>>In Progress</option>
                                    <option value="On Hold" <%= task.getTrack().equals("On Hold") ? "selected" : "" %>>On Hold</option>
                                    <option value="Completed" <%= task.getTrack().equals("Completed") ? "selected" : "" %>>Completed</option>
                                    <option value="Assigned" <%= task.getTrack().equals("Assigned") ? "selected" : "" %>>Assigned</option>
                                </select>
                              
                            </form>
                        </td>
                        <td>
                            <button class="btn btn-primary btn-sm" 
                                    onclick="openExtensionModal('<%= task.getTaskId() %>')" 
                                    <%= task.isExtensionRequested() ? "disabled" : "" %>> <!-- Disable if extension requested -->
                                Request Extension
                            </button>
                        </td>
                    </tr>
                      <%} %>
                    <%
                        }
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</section>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
 
		<!-- Deadline Extension Request Modal -->
		<div class="modal fade" id="extensionModal" tabindex="-1"
			aria-labelledby="extensionModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="extensionModalLabel">Request
							Deadline Extension</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/dimonex/tasks/requestExtension" method="post" id="extensionForm">
							<input type="hidden" id="taskId" name="taskId"> <label
								for="newDeadline">New Deadline:</label> <input type="date"
								id="newDeadline" name="newDeadline" class="form-control"
								required>
							<button type="submit" class="btn btn-success mt-3">Submit
								Request</button>
						</form>
					</div>
				</div>
			</div>
		</div>

















	<%
	int salary=(int) request.getAttribute("salary");
	
	%>


		<!-- Salary Section -->
		<section id="salary-section">
			<div class="card">
				<div class="card-header">
					<h5>Salary</h5>
				</div>
				<div class="card-body">
					<p>
						<strong>Total Salary for this Month:</strong> <%= salary %>
					</p>
					<p>
						<strong>Last Payment:</strong> 2025-01-30
					</p>
					<p>
						<strong>Status:</strong> Paid
					</p>
				</div>
			</div>
		</section>

		<!-- Equipment Section -->

<section id="equipment-section">
    <div class="card">
        <div class="card-header">
            <h5>My Equipment</h5>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Equipment Name</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Assigned Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty equipmentList}">
                            <c:forEach var="equipment" items="${equipmentList}">
                                <tr>
                                    <td>${equipment.name}</td>
                                    <td>${equipment.type}</td>
                                    <td>${equipment.availabilityStatus}</td>
                                     <td>
                                        <fmt:formatDate value="${equipment.assignedDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
                                    </td>
                                    
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="text-center">No Equipment Assigned</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</section>


	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.js"></script>




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
                events: [
                    <%List<Attendance> filteredAttendance = (List<Attendance>) request.getAttribute("attendanceList");%>
                    <% System.out.println("----------------******************************************-------------------------------------------------"+filteredAttendance); %>
                    <%for (Attendance record : filteredAttendance) {%>
                    {
                        title: '<%=record.getStatus()%>',
                        start: '<%=record.getDate()%>'
                    },
                    <%}%>
                ]
            });
            calendar.render();
        });
        
        function openExtensionModal(taskId) {
            document.getElementById('taskId').value = taskId;
            var extensionModal = new bootstrap.Modal(document.getElementById('extensionModal'));
            extensionModal.show();
        }
    </script>
</body>
</html>


