<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="menu-item-list-customer.css">
</head>
<body>
<div id="main">
        <header>
            <div id="header-container">
                <div class="col-3"><span>truYum</span></div>
                <div class="col-3"></div>
                <div class="col-3"> 
                    <ul class="menu">
                        <li><a  id="link" href="www.google.com">Menu</a></li>
                        <li><a id="link" href="www.google.com">Cart</a></li>
                    </ul>
            </div>
        </div>
        </header>
        
        <div id="menu">
            <div id="menu-item">Products</div>
            <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Free Delivery</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
        	</thead>
        	<tbody>
	        	<c:forEach items="${menuItemListCustomer}" var = "menuItem">
		        	<tr>
		        		<td id="row-one">${ menuItem.name}</td>
		        		<td>${ menuItem.freeDelivery}</td>
		        		<td id="row-two">${ menuItem.price}</td>
		        		<td>${ menuItem.category}</td>
		        		<td><a href="www.google.com">Add To Cart</a></td>
		        	</tr>
		        	</c:forEach>
        	</tbody>
            </table>
        </div>
        
    </div>
    <footer>Copyright &copy; 2019</footer>
</body>
</html>