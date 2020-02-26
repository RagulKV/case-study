<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>truYum</title>
     <link rel="stylesheet" type="text/css" href="menu-item-list-admin.css">
</head>
<body>
    <div id="main">
         <header>
            <div id="header-container">
                <div class="col-3"><span>truYum</span></div>
                <div class="col-3"></div>
                <div class="col-3"> <a class="menu" href="/show-menu-list-admin">Menu</a></div>
            </div>
        </header> 
        
        <div id="menu">
            <div id="menu-item">Menu Items</div>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Active</th>
                    <th>Date of Launch</th>
                    <th>Category</th>
                    <th>Free Delivery</th>
                    <th>Action</th>
                    </tr>
                </thead>
        	<tbody>
        	<c:forEach items="${menuItemListAdmin}" var = "menuItem">
	        	<tr>
	        		<td id="row-one">${ menuItem.name}</td>
	        		<td id="row-two">${ menuItem.price}</td>
	        		<td>${ menuItem.active}</td>
	        		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${menuItem.dateOfLaunch}" /></td>
	        		<td>${ menuItem.category}</td>
	        		<td>${ menuItem.freeDelivery}</td>
	        		<td><a href="show-edit-menu-item?id=${menuItem.id}">Edit</a></td>
	        	</tr>
        	</c:forEach>
                
                </tbody>
            </table>
        </div>
        
    </div>
    <footer>Copyright &copy; 2019</footer>
    
</body>
</html>