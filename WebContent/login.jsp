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
else
{
	%>

	<form action=<%=response.encodeURL("Controller?action=doLogin") %> method="post">
		Email: <input type="text" name = "email"></input>
		Password: <input type="text" name = "password"></input>
		<input type="submit" value="Login" />
	</form>
	
	<%
	if(request.getAttribute("validationmessage")==null)
		request.setAttribute("validationmessage",""); 
	%>
	
	<h3><%=request.getAttribute("validationmessage") %></h3>

	<%
} 

%>
</body>
</html>