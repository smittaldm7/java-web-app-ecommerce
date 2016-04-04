<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import ="model.Order, model.OrderItem, java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table{border: 1px solid black;}
table.order{margin:10px;}
table.orderitem{margin: 30px;}
</style>
<body>
<%
String message= null;
if( (message = (String)request.getAttribute("message"))!=null)
	{
	out.println(message);	
	}		
else
	{

		List<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
		if(orders.isEmpty())
		{
			out.println("No previous orders");
		}
		else
		{	
			for(Order order:orders)
				{
				%>
				<table class="order">
					<tr>
						<td>Order Id</td>
						<td><% out.println(order.getId());%></td> 
					<tr/>
					<tr>
						<td>Date of Purchase</td>
						<td><% out.println(order.getDate());%></td> 
					<tr/>
					<tr>
						<td>Bill Amount</td>
						<td><% out.println(order.getTotalBillAmount());%></td> 
					<tr/>
					<% 
					for(OrderItem oi:order.getOrderItems())
						{
						%>
						
						<table class = "orderitem"> 
						<tr class ="row">
							<td>Product Name<td/>
							<td><%out.println(oi.getProductName());%><td/> 
						<tr/>
						<tr class ="row">
							<td>Supplier Name<td/>
							<td><%out.println(oi.getSupplierName());%><td/> 
						<tr/>
						<tr class ="row">
							<td>Quantity<td/>
							<td><%out.println(oi.getQuantity());%><td/> 
						<tr/>
						<tr class ="row">
							<td>Item Price After Discount<td/>
							<td><%out.println(oi.getItemPriceAfterDiscount());%><td/> 
						<tr/>
						<tr class ="row">
							<td>Total Price After Discount<td/>
							<td><%out.println(oi.getTotalPriceAfterDiscount());%><td/> 
						<tr/>
						</table>
						<%
						}
					%>
					</table><%
					}
				
		}
	}
%>
</body>
</html>