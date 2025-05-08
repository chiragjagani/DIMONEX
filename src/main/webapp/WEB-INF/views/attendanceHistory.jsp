<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.stpl.dimonex.model.Attendance"%>
<%@ page import="com.stpl.dimonex.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendance History</title>
</head>
<body>
	<h1>Attendance History</h1>
	<%
	List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
	String month = (String) request.getAttribute("month");
	User user = (User) request.getAttribute("user");
	%>
	<!-- Form for selecting the month -->
	<form action="/dimonex/attendance/history" method="GET">
		<label for="month">Select Month:</label> <select name="month"
			id="month">
			<option value="01">January</option>
			<option value="02">February</option>
			<option value="03">March</option>
			<option value="04">April</option>
			<option value="05">May</option>
			<option value="06">June</option>
			<option value="07">July</option>
			<option value="08">August</option>
			<option value="09">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
			<input type="hidden" name="userId" value=<%= user.getId() %>>
		</select> <br> <input type="submit" value="Show Attendance">
	</form>

	<br>

	<!-- Display the attendance history for the selected month -->

	<%
	if (attendanceList != null && !attendanceList.isEmpty()) {
	%>
	<h3>
		Attendance for User:
		<%=user.getUsername()%>- Month:
		<%=month%></h3>
	<table border="1">
		<thead>
			<tr>
				<th>Date</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Attendance attendance : attendanceList) {
			%>
			<tr>
				<td><%=attendance.getDate()%></td>
				<td><%=attendance.getStatus()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	} else {
	%>
	<p>No attendance data found for this month.</p>
	<%
	}
	%>

</body>
</html>
