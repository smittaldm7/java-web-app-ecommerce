<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import ="model.CartItem, java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String message= null;
if( (message = (String)request.getAttribute("message"))!=null)
	{
	out.println(message);	
	}		
else
	{

		List<CartItem> cartItems = (ArrayList<CartItem>)request.getAttribute("cartitems");
		if(cartItems.isEmpty())
		{
			out.println("Cart is empty");
		}
		else
		{	
			float totalAmount = 0.0f;
			for(CartItem ci:cartItems)
				{
				out.println(ci.getProductName());%> <br/><%
				out.println(ci.getSupplierName());%> <br/><%
				out.println(ci.getQuantity());%> <br/><%
				out.println(ci.getItemPriceAfterDiscount());%> <br/><%
				out.println(ci.getTotalPriceAfterDiscount());%> <br/><%
				totalAmount+=ci.getTotalPriceAfterDiscount();
				}
			%><br/>Total Amount<%out.println(totalAmount);
		
			%>
			<form action="<%=response.encodeURL("Controller?action=placeOrder")%>" method="post">Place Order
				<input type="submit" value="Place Order"/>
			</form>
			
			<form action="<%=response.encodeURL("Controller?action=clearCart")%>" method="post">Clear Cart
				<input type="submit" value="Clear Cart"/>
			</form>
			
			<% 
		}
	}
%>

</body>
</html>