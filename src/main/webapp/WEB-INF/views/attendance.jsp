<%-- <%@page import="com.stpl.dimonex.model.User"%>
<%@page import="com.stpl.dimonex.model.Attendance"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance Management</title>
</head>
<body>
    <h1>Attendance Management</h1>

    <!-- Display Table of Users for Attendance -->
    <form action="/attendance/submit" method="POST">
        <table border="1">
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                %>
                    <tr>
                        <td><%= user.getUsername() %></td>
                        <td>
                            <select name="attendanceStatus_<%= user.getId() %>" required>
                                <option value="Present">Present</option>
                                <option value="Absent">Absent</option>
                                <option value="On Leave">On Leave</option>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" name="userId_<%= user.getId() %>" value="<%= user.getId() %>"/>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        
        <br>
        <input type="submit" value="Submit Attendance"/>
    </form>

    <br>
    <!-- Display existing attendance records -->
    <%
        List<Attendance> attendances = (List<Attendance>) request.getAttribute("attendances");
        if (attendances != null) {
            for (Attendance attendance : attendances) {
    %>
        <p>
            <%= attendance.getUserId() %> - <%= attendance.getStatus() %> 
            <a href="/attendance/delete/<%= attendance.getId() %>">Delete</a>
        </p>
    <%
            }
        }
    %>

</body>
</html>
 --%>



<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.stpl.dimonex.model.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendance Management</title>
<style>
button {
	padding: 10px 20px;
	font-size: 14px;
	cursor: pointer;
}

.present {
	background-color: #4CAF50; /* Green */
	color: white;
}

.absent {
	background-color: #f44336; /* Red */
	color: white;
}
</style>
</head>
<body>
	<h1>Attendance Management - Today</h1>

	<form action="/dimonex/attendance/submit" method="POST">
		<table border="1">
			<thead>
				<tr>
					<th>User Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<User> users = (List<User>) request.getAttribute("users");
				if (users != null) {
					for (User user : users) {
				%>
				<tr>
					<td><%=user.getUsername()%></td>
					<td>
						<button type="submit" name="attendanceStatus_<%=user.getId()%>"
							value="Present" class="present">Present</button>
						<button type="submit" name="attendanceStatus_<%=user.getId()%>"
							value="Absent" class="absent">Absent</button> <input
						type="hidden" name="userId_<%=user.getId()%>"
						value="<%=user.getId()%>" />
					</td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>

		<br> <input type="submit" value="Submit Attendance">
	</form>

</body>
</html>
