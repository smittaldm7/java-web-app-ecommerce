<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="model.Customer" %>
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
	Customer customer = (Customer)request.getAttribute("customer");
	out.println(""+customer.getName());%><br/><%
	out.println(""+customer.getEmail());%><br/><%
	out.println(""+customer.getPassword());%><br/><%
	out.println(""+customer.getAddress());%><br/><%
	out.println(""+customer.getPhoneNumber());%><br/><%
	}
	%>
</body>
</html>