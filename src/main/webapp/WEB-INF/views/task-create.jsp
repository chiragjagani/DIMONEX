<!-- <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Task</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4">Create Task</h1>
        <form action="/dimonex/tasks/save" method="post">
            <div class="form-group">
                <label for="taskName">Task Name:</label>
                <input type="text" id="taskName" name="taskName" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="deadline">Deadline:</label>
                <input type="date" id="deadline" name="deadline" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="track">Track:</label>
                <input type="text" id="track" name="track" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Save Task</button>
        </form>
        <a href="/tasks" class="btn btn-secondary mt-3">Back to Task List</a>
    </div>
</body>
</html>
 -->


<%@page import="com.stpl.dimonex.service.UserService"%>
<%@page import="com.stpl.dimonex.model.User"%>
<%@page import="com.stpl.dimonex.model.Department"%>
<%@page import="com.stpl.dimonex.model.Polisher"%>
<%@page import="com.stpl.dimonex.model.Manager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Task</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="my-4">Create Task</h1>
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
				<label for="dimonds">No of Dimonds:</label>
				<input id="dimonds" name="dimonds" class="form-control"/>
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
					Manager managers = (Manager) request.getAttribute("managers");
					
					/* List<User> users = (List<User>) request.getAttribute("user");
					
					System.out.println(users); */
									/* UserService userService = new UserService();
					userService.getUser(manager.getUser().getId());
 */					%>
					<option value="<%= managers.getId()%>"><%=managers.getUser().getUsername()%></option>
					
				</select>
			</div>
			<div class="form-group">
				<label for="polisher">Assign Polisher:</label> <select id="polisher"
					name="polisherId" class="form-control" required>
					<%
					List<Polisher> polishers = (List<Polisher>) request.getAttribute("polishers");
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
				<label for="department">Select Department:</label> 
				<select
					id="department" name="departmentId" class="form-control" required>
					<%
					Department departments = (Department) request.getAttribute("department");
					%>
					
					<option value="<%=departments.getId()%>"><%=departments.getName()%>
					</option>
					
				</select>
			</div>
			<button type="submit" class="btn btn-success">Save Task</button>
		</form>
		<a href="/tasks" class="btn btn-secondary mt-3">Back to Task List</a>
	</div>
</body>
</html>
