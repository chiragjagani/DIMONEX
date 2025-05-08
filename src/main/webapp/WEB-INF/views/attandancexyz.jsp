<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.stpl.dimonex.model.Attendance"%>
<%@page import="com.stpl.dimonex.model.User"%>
<%@page import="com.stpl.dimonex.model.Manager"%>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Attendance</title>
</head>
<body>
    <h2>Attendance Form</h2>
    <form action="<%= request.getContextPath() %>/attendance/attsubmit" method="post">
        <div>
            <label for="attendanceDate">Attendance Date:</label>
            <input type="date" id="attendanceDate" name="attendanceDate" value="<%= request.getAttribute("attendanceDate") %>" onchange="fetchAttendanceData()">
        </div>

        <div>
            <label for="manager">Manager:</label>
            <select name="manager">
                <% List<Manager> managers = (List<Manager>) request.getAttribute("managers"); %>
                <% for (Manager manager : managers) { %>
                    <option value="<%= manager.getId() %>"><%= manager.getUser().getUsername() %></option>
                <% } %>
            </select>
        </div>

        <div>
            <h3>Select Attendance for Polishers:</h3>
            <% List<User> users = (List<User>) request.getAttribute("users"); %>
            <div id="attendanceRecords">
                <% for (User user : users) { %>
                    <div>
                        <label><%= user.getUsername() %>:</label>
                        <input type="radio" name="attendanceStatus_<%= user.getId() %>" value="Present"> Present
                        <input type="radio" name="attendanceStatus_<%= user.getId() %>" value="Absent"> Absent
                    </div>
                <% } %>
            </div>
        </div>

        <button type="submit">Submit Attendance</button>
    </form>

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
                	
                	
                	
                    // Iterate over the returned attendance data and pre-select the radio buttons
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
                        }
                    });
                }
            });
        }

        // Trigger initial fetch on page load (for today by default)
        $(document).ready(function() {
            fetchAttendanceData();
        });
    </script>
</body>
</html>
