<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stpl.dimonex.model.Attendance" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid black;
        }
        .present {
            background-color: #4CAF50;
            color: white;
        }
        .absent {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <h1>Attendance List</h1>

    <p>Date: <%= request.getAttribute("attendanceDate") %></p>

    <h3>Summary: Present = <%= request.getAttribute("presentCount") %>, Absent = <%= request.getAttribute("absentCount") %></h3>

    <table>
        <thead>
            <tr>
                <th>User Name</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Attendance> attendances = (List<Attendance>) request.getAttribute("attendances");
                if (attendances != null) {
                    for (Attendance attendance : attendances) {
            %>
                <tr>
                    <td><%= attendance.getUserId() %></td>
                    <td class="<%= attendance.getStatus().equals("Present") ? "present" : "absent" %>">
                        <%= attendance.getStatus() %>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

</body>
</html>
