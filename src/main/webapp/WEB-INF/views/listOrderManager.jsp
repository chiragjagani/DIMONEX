<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Orders</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	margin: 20px;
	text-align: center;
}

h2 {
	color: #333;
}

.container {
	width: 80%;
	margin: auto;
	overflow: hidden;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background: white;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	overflow: hidden;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background: #007bff;
	color: white;
	font-weight: bold;
}

tr:hover {
	background: #f1f1f1;
}

.no-orders {
	margin-top: 20px;
	font-size: 18px;
	color: #d9534f;
	font-weight: bold;
}

select, button {
	padding: 6px;
	font-size: 14px;
	margin: 4px;
}

button {
	background: #28a745;
	color: white;
	border: none;
	cursor: pointer;
	padding: 8px 12px;
	border-radius: 5px;
}

button:hover {
	background: #218838;
}

.back-btn {
	margin-top: 20px;
	background: #dc3545;
	padding: 10px 15px;
	font-size: 16px;
	border-radius: 5px;
	cursor: pointer;
	color: white;
	text-decoration: none;
	display: inline-block;
}

.back-btn:hover {
	background: #c82333;
}
</style>
</head>
<body>

	<div class="container">
		<h2>Orders Managed by You</h2>

		<table>
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Customer Name</th>
					<th>Order Date</th>
					<th>Amount</th>
					<th>Type</th>
					<th>Status</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${managerOrders}">
					<tr>
						<td>${order.id}</td>
						<td>${order.customer.name}</td>
						<td>${order.orderDate}</td>
						<td>${order.amount}$</td>
						<td>${order.type}</td>
						<td>
							<!-- Form for updating status -->
							<form
								action="${pageContext.request.contextPath}/manager/updateOrderStatus"
								method="post">
								<input type="hidden" name="orderId" value="${order.id}">
								<select name="newStatus">
									<option value="Not Started"
										${order.status == 'Not Started' ? 'selected' : ''}>Not
										Started</option>
									<option value="Working"
										${order.status == 'Working' ? 'selected' : ''}>Working</option>
									<option value="Finished"
										${order.status == 'Finished' ? 'selected' : ''}>Finished</option>
								</select>
								<button type="submit">Update</button>
							</form>
						</td>
						<td>${order.quantity}</td>
						<td><a
							href="${pageContext.request.contextPath}/order/downloadInvoice?orderId=${order.id}"
							class="btn btn-primary">Download Invoice</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- Button to download all orders -->
		<a href="${pageContext.request.contextPath}/order/downloadAllInvoices"
			class="back-btn" style="background: #17a2b8;"> Download All
			Orders Invoice </a>

		<c:if test="${empty managerOrders}">
			<p class="no-orders">No orders found for this manager.</p>
		</c:if>

		<!-- Go Back Button -->
		<a href="${pageContext.request.contextPath}/manager/${id}"
			class="back-btn">Go Back to Dashboard</a>
	</div>

</body>
</html>
