<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Dashboard - Equipment Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Manager Dashboard - Equipment Management</h2>

        <!-- Add Equipment Form -->
        <div class="card mt-4">
            <div class="card-header bg-success text-white">Add New Equipment</div>
            <div class="card-body">
                <form id="addEquipmentForm">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="equipmentName" class="form-label">Equipment Name:</label>
                            <input type="text" id="equipmentName" class="form-control" required>
                        </div>
                        <div class="col-md-4">
                            <label for="equipmentType" class="form-label">Equipment Type:</label>
                            <input type="text" id="equipmentType" class="form-control" required>
                        </div>
                        <div class="col-md-4">
                            <label for="equipmentStatus" class="form-label">Available:</label>
                            <select id="equipmentStatus" class="form-select">
                                <option value="true">Yes</option>
                                <option value="false">No</option>
                            </select>
                        </div>
                    </div>
                    <div class="mt-3">
                        <button type="button" class="btn btn-primary" onclick="addEquipment()">Add Equipment</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Equipment List -->
        <div class="card mt-4">
            <div class="card-header bg-secondary text-white">Available Equipment</div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Available</th>
                        </tr>
                    </thead>
                    <tbody id="equipmentList">
                        <!-- Equipment List Fetched via AJAX -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function addEquipment() {
            let name = $("#equipmentName").val();
            let type = $("#equipmentType").val();
            let availableStatus = $("#equipmentStatus").val();

            if (!name || !type) {
                alert("Please fill all fields!");
                return;
            }

            $.ajax({
                type: "POST",
                url: "/api/equipment/add",
                data: { name: name, type: type, availableStatus: availableStatus },
                success: function(response) {
                    alert(response);
                    loadEquipment(); // Refresh Equipment List
                },
                error: function() {
                    alert("Error adding equipment. Try again!");
                }
            });
        }

        function loadEquipment() {
            $.ajax({
                type: "GET",
                url: "/api/equipment/all",
                success: function(data) {
                    let tableContent = "";
                    data.forEach(function(item) {
                        tableContent += `
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.type}</td>
                                <td>${item.availableStatus ? "Yes" : "No"}</td>
                            </tr>
                        `;
                    });
                    $("#equipmentList").html(tableContent);
                },
                error: function() {
                    alert("Error fetching equipment.");
                }
            });
        }

        // Load equipment when the page loads
        $(document).ready(function() {
            loadEquipment();
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
