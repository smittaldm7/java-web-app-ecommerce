<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action=<%=response.encodeURL("Controller?action=doCreate")%> method="post">


Email: <input type="text" name = "email"></input>
Password: <input type="text" name = "password"></input>
Repeat Password: <input type="text" name = "repeatpassword"></input>

<input type="submit" value="submit" />
</form>

<%
if(request.getAttribute("validationmessage")==null)
request.setAttribute("validationmessage",""); 
%>

<h3><%=request.getAttribute("validationmessage") %></h3>
</body>
</html>