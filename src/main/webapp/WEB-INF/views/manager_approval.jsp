<%@page import="com.stpl.dimonex.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.stpl.dimonex.model.Attendance"%>
<%@page import="com.stpl.dimonex.model.Department"%>
<%@page import="com.stpl.dimonex.model.Polisher"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Approval</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Extension Requests</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Task Name</th>
                    <th>Current Deadline</th>
                    <th>Requested Extension</th>
                    <th>Polisher</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Task> extensionRequests = (List<Task>) request.getAttribute("extensionRequests");
                for (Task task : extensionRequests) { 
                %>
                <tr>
                    <td><%= task.getTaskName() %></td>
                    <td><%= task.getDeadline() %></td>
                    <td><%= task.getRequestedExtensionDeadline() %></td>
                    <td><%= task.getPolisher().getUser().getUsername() %></td>
                    <td>
                        <form action="/dimonex/manager/approveExtension" method="post">
                            <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                            <button type="submit" name="decision" value="approved" class="btn btn-success">Approve</button>
                            <button type="submit" name="decision" value="rejected" class="btn btn-danger">Reject</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
