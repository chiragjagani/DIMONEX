<!-- <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
</head>
<body>

<h2>Register</h2>

<form action="/dimonex/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="role">Role:</label>
    <select name="role" id="role" required>
        <option value="manager">Manager</option>
        <option value="polisher">Polisher</option>
        <option value="admin">Admin</option>
    </select><br><br>
    
    <input type="submit" value="Register">
</form>

<p>Already have an account? <a href="/login">Login here</a></p>

</body>
</html>
 -->
 
 
 
 
 
 
 
 
 
 
 
<!--  <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    
    Bootstrap CSS
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .register-container {
            max-width: 400px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-custom {
            width: 100%;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="register-container">
        <h2>Register</h2>
        
        <form action="/dimonex/register" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <select name="role" id="role" class="form-select" required>
                    <option value="manager">Manager</option>
                    <option value="polisher">Polisher</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            
            <button type="submit" class="btn btn-primary btn-custom">Register</button>
        </form>
        
        <p class="mt-3 text-center">Already have an account? <a href="/dimonex/login">Login here</a></p>
    </div>
</div>

Bootstrap JS
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html> -->
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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
    <title>Register</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .register-container {
            max-width: 400px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-custom {
            width: 100%;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="register-container">
        <h2>Register</h2>
        
        <form action="/dimonex/register" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            
            <div class="mb-3">
                <label for="role" class="form-label">Role</label>
                <select name="role" id="role" class="form-select" required>
                    <option value="manager">Manager</option>
                    <option value="polisher">Polisher</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <select name="department" id="department" class="form-select" required>
                    <% 
                        List<Department> departments = (List<Department>) request.getAttribute("departments");
                      System.out.println(departments);
                		if (departments != null) {
                            for (Department dept : departments) { %>
                                <option value="<%= dept.getId() %>"><%= dept.getName() %></option>
                    <%      }
                        }
                    %>
                </select>
            </div>
            
            <button type="submit" class="btn btn-primary btn-custom">Register</button>
        </form>
        
        <p class="mt-3 text-center">Already have an account? <a href="login">Login here</a></p>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

 