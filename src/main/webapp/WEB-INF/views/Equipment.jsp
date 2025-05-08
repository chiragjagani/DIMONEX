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

        <!-- Equipment Assignment Section -->
        <div class="card mt-4">
            <div class="card-header bg-primary text-white">Assign Equipment to Polisher</div>
            <div class="card-body">
                <form id="assignForm">
                    <div class="row">
                        <div class="col-md-5">
                            <label for="equipmentSelect" class="form-label">Select Equipment:</label>
                            <select id="equipmentSelect" class="form-select">
                                <option value="">-- Select Equipment --</option>
                                <c:forEach var="equipment" items="${availableEquipment}">
                                    <option value="${equipment.id}">${equipment.name} (${equipment.type})</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-5">
                            <label for="polisherSelect" class="form-label">Select Polisher:</label>
                            <select id="polisherSelect" class="form-select">
                                <option value="">-- Select Polisher --</option>
                                <c:forEach var="polisher" items="${polishers}">
                                    <option value="${polisher.id}">${polisher.user.username}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2 d-flex align-items-end">
                            <button type="button" class="btn btn-success" onclick="assignEquipment()">Assign</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Assignment Logs -->
        <div class="card mt-4">
            <div class="card-header bg-secondary text-white">Equipment Assignment Logs</div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Equipment</th>
                            <th>Polisher</th>
                            <th>Assigned Date</th>
                        </tr>
                    </thead>
                    <tbody id="assignmentLogs">
                        <c:forEach var="log" items="${assignments}">
                            <tr>
                                <td>${log.id}</td>
                                <td>${log.equipment.name}</td>
                                <td>${log.polisher.user.username}</td>
                                <td>${log.assignedDate}</td>
                                <td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
    function assignEquipment() {
        let equipmentId = $("#equipmentSelect").val();
        let polisherId = $("#polisherSelect").val();

        console.log("Selected Equipment ID:", equipmentId);
        console.log("Selected Polisher ID:", polisherId);

        if (!equipmentId || !polisherId) {
            alert("Please select both Equipment and Polisher!");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/dimonex/api/equipment/assign",
            contentType: "application/json",
            data: JSON.stringify({
                equipmentId: equipmentId,
                polisherId: polisherId
            }),
            success: function(response) {
                alert("Assignment Successful: " + response);
                location.reload(); // Refresh page to update logs
            },
            error: function(xhr, status, error) {
                console.error("Error Response:", xhr.responseText);
                alert("Error in assignment. Try again!");
            }
        });
    }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>
