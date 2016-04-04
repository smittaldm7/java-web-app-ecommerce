<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List, model.Product " %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<br/>
<%

List<Product> products = (List<Product>)request.getAttribute("productList");
if(products.isEmpty())
	{
	out.println("No matching products found");
	}
else
	{	
	out.println("Results");
	for (Product p:products)
	{
	%>
	<form action="<%=response.encodeURL("Controller?action=viewProduct")%>" method="post">
	<input type="hidden" name="productName" value="<%out.print(p.getName()); %>" />
	<input type= "Submit" value="<%out.print(p.getName());%>"/>
	
	
	</form>
	<%}
}
%>
</body>
</html>