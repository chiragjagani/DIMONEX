package com.stpl.dimonex.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stpl.dimonex.model.Customer;
import com.stpl.dimonex.model.Manager;
import com.stpl.dimonex.model.Order;
import com.stpl.dimonex.service.CustomerService;
import com.stpl.dimonex.service.ManagerService;
import com.stpl.dimonex.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ManagerService managerService;

	@GetMapping("/placeOrder")
	public String showPlaceOrderPage(@RequestParam("userId") Long userId, Model model) {
		model.addAttribute("userId", userId);
		List<Manager> managers = managerService.getAllManagers(); // Fetch all managers
		model.addAttribute("managers", managers);

		return "placeOrder"; // This will resolve to /WEB-INF/views/placeOrder.jsp
	}

	@PostMapping("/saveOrder")
	public String saveOrder(@RequestParam String customerName, @RequestParam String customerContact,
			@RequestParam String orderType, @RequestParam String orderStatus, @RequestParam double price,
			@RequestParam String orderDate, @RequestParam int quantity, @RequestParam Long managerId,
			@RequestParam("userId") Long userId) {

		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setContactInfo(customerContact);
		Customer savedCustomer = customerService.saveCustomer(customer);

		Order order = new Order();
		order.setCustomer(savedCustomer);
		order.setType(orderType);
		order.setStatus(orderStatus);
		order.setAmount(price);

		order.setOrderDate(orderDate);
		order.setQuantity(quantity);
		Manager manager = managerService.getManagerByUserId(managerId);
		System.out.print("manager id:" + manager.getId());
		order.setManager(manager);

		orderService.saveOrUpdateOrder(order);
		System.out.print("-----SAVE ORDER PAGE" + userId);
		return "redirect:/admin/dashboard?userId=" + userId;
	}

	@GetMapping("/listOrder")
	public String listOrders(@RequestParam("userId") Long userId, Model model) {
		List<Order> orders = orderService.getAllOrders();

		model.addAttribute("orders", orders);
		model.addAttribute("userId", userId); // To keep user session

		return "listOrder"; // This will map to listOrders.jsp
	}

	@GetMapping("/editOrder")
	public String editOrder(@RequestParam("orderId") Long orderId, @RequestParam("userId") Long userId, Model model) {
		Order order = orderService.getOrderById(orderId);

		List<Manager> managers = managerService.getAllManagers(); // Fetch all managers
		model.addAttribute("managers", managers);
		model.addAttribute("order", order);
		model.addAttribute("userId", userId); // Maintain session
		return "editOrder"; // This will map to editOrder.jsp
	}

	@PostMapping("/updateOrder")
	public String updateOrder(@RequestParam Long orderId, @RequestParam String orderType,
			@RequestParam String orderStatus, @RequestParam int quantity, @RequestParam double amount,
			@RequestParam String orderDate, @RequestParam Long managerId, @RequestParam("userId") Long userId) {

		Order order = orderService.getOrderById(orderId);
		order.setType(orderType);
		order.setStatus(orderStatus);
		order.setQuantity(quantity);
		order.setAmount(amount);
		order.setOrderDate(orderDate);
		Manager manager = managerService.getManagerByUserId(managerId);
		System.out.print("manager id:" + manager.getId());
		order.setManager(manager);

		orderService.saveOrUpdateOrder(order);
		return "redirect:/order/listOrder?userId=" + userId;
	}

	@GetMapping("/downloadInvoice")
	public void downloadInvoice(@RequestParam("orderId") Long orderId, HttpServletResponse response)
			throws IOException, DocumentException {
		Order order = orderService.getOrderById(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
			return;
		}

		// Setting response type
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=invoice_" + orderId + ".pdf");

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);

		document.open();

		// Add title
		Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
		Paragraph title = new Paragraph("Invoice for Order #" + orderId, titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph("\n"));

		// Create table
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);

		// Add rows
		addTableRow(table, "Customer Name", order.getCustomer().getName());
		addTableRow(table, "Customer Contact", order.getCustomer().getContactInfo());
		addTableRow(table, "Order Type", order.getType());
		addTableRow(table, "Order Status", order.getStatus());
		addTableRow(table, "Quantity", String.valueOf(order.getQuantity()));
		addTableRow(table, "Total Price", "Rs. " + order.getAmount());
		addTableRow(table, "Order Date", order.getOrderDate());

		document.add(table);
		document.add(
				new Paragraph("\nThank you for your business!", new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC)));

		document.close();

		// Write PDF to response output stream
		response.getOutputStream().write(out.toByteArray());
		response.getOutputStream().flush();
	}

	private void addTableRow(PdfPTable table, String key, String value) {
		PdfPCell cellKey = new PdfPCell(new Phrase(key));
		cellKey.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cellKey);
		table.addCell(new PdfPCell(new Phrase(value)));
	}

	@GetMapping("/downloadAllInvoices")
	public void downloadAllInvoices(HttpServletResponse response) throws IOException, DocumentException {
		List<Order> orders = orderService.getAllOrders(); // Or filter as needed

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=all_orders_invoice.pdf");

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);

		document.open();

		Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
		Paragraph title = new Paragraph("All Orders Invoice Summary", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph("\n"));

		PdfPTable table = new PdfPTable(7); // 7 columns for all order fields
		table.setWidthPercentage(100);

		// Header Row
		Stream.of("Order ID", "Customer", "Date", "Amount", "Type", "Status", "Qty").forEach(header -> {
			PdfPCell cell = new PdfPCell(new Phrase(header));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
		});

		// Order Rows
		for (Order order : orders) {
			table.addCell(order.getId().toString());
			table.addCell(order.getCustomer().getName());
			table.addCell(order.getOrderDate());
			table.addCell("Rs. " + order.getAmount());
			table.addCell(order.getType());
			table.addCell(order.getStatus());
			table.addCell(String.valueOf(order.getQuantity()));
		}

		document.add(table);
		document.add(new Paragraph("\nGenerated on: " + new java.util.Date(), new Font(Font.FontFamily.HELVETICA, 10)));
		document.close();

		response.getOutputStream().write(out.toByteArray());
		response.getOutputStream().flush();
	}

	@GetMapping("/monthlyChart")
	public String showMonthlyChart(@RequestParam("userId") Long userId, Model model) {
		List<Order> allOrders = orderService.getAllOrders();

		// Use correct formatter for '2025-03-09'
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Map<String, Long> monthlyOrderCount = allOrders.stream().collect(Collectors.groupingBy(order -> {
			System.out.println(order.getOrderDate());
			LocalDate date = LocalDate.parse(order.getOrderDate(), formatter);
			return date.getMonth().toString() + " " + date.getYear();
		}, Collectors.counting()));

		// Sorting the map (optional)
		LinkedHashMap<String, Long> sortedMap = monthlyOrderCount.entrySet().stream().sorted((e1, e2) -> {
			String[] parts1 = e1.getKey().split(" ");
			String[] parts2 = e2.getKey().split(" ");

			int year1 = Integer.parseInt(parts1[1]);
			int year2 = Integer.parseInt(parts2[1]);

			String month1Formatted = parts1[0].substring(0, 1).toUpperCase() + parts1[0].substring(1).toLowerCase();
			String month2Formatted = parts2[0].substring(0, 1).toUpperCase() + parts2[0].substring(1).toLowerCase();

			int month1 = LocalDate.parse("01 " + month1Formatted + " " + parts1[1],
					DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)).getMonthValue();
			int month2 = LocalDate.parse("01 " + month2Formatted + " " + parts2[1],
					DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)).getMonthValue();

			return year1 != year2 ? year1 - year2 : month1 - month2;
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		model.addAttribute("userId", userId);
		model.addAttribute("months", sortedMap.keySet());
		model.addAttribute("counts", sortedMap.values());

		return "monthlyChart";
	}

}
