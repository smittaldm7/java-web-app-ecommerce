<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.List,model.SupplierProduct" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
<% 

List<SupplierProduct> supplierProductList =
		(List<SupplierProduct>)request.getAttribute("supplierProductList");  

out.println(supplierProductList.get(0).getProductName());
%><br/><% 
for(SupplierProduct sp:supplierProductList)
	{
	out.println("Supplier: "+sp.getSupplierName());
	out.println("Price:" +sp.getPrice());
	out.println("Discount: "+ sp.getDiscountPercent());
	out.println("Stock: "+ sp.getStock());
	if(sp.getStock()>0)
		{
		%>
		<form action="<%=response.encodeURL("Controller?action=addToCart")%>" method="post">
			Quantity <input type="number" name="quantity" min="1" 
													max="<%=sp.getStock()%>">
			<input type="hidden" name="productName" 
							value="<%out.print(sp.getProductName()); %>" />
			<input type="hidden" name="supplierName" 
							value="<%out.print(sp.getSupplierName()); %>" />
			<input type= "Submit" value="Add to Cart"/>
		</form><br/>
		<% 
		}
	
	}
%>

</body>
</html>