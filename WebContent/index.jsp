<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

if(request.getSession().getAttribute("email")!=null)
	{
	out.println(request.getSession().getAttribute("email"));
	}

%>
<br/>
<form action=<%=response.encodeURL("Controller?action=doSearch") %> method="post">
Search for a product: <input type="text" name = "searchText"></input>
<input type="submit" value="Go" />
</form>
<%
if(request.getSession().getAttribute("email")==null)
	{
	%>	
	<a href="<%=response.encodeURL("Controller?action=login") %>">Go to Login Page</a>
	<br/>
	<%
	}
else
	{
	%>
	
	<a href="<%=response.encodeURL("Controller?action=logout") %>">Logout</a>
	<br/>
	<a href="<%=response.encodeURL("Controller?action=viewCart") %>">View Cart</a>
	<br/>
	<a href="<%=response.encodeURL("Controller?action=viewAccount") %>">Account Details</a>
	<br/>
	<a href="<%=response.encodeURL("Controller?action=viewOrders") %>">Order History</a>
	<br/>
	<%
	}
%>
</body>
</html>