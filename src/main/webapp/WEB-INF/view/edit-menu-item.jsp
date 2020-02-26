<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Menu Item</title>
<link rel="stylesheet" type="text/css" href="edit-menu.css">
</head>
<body>
<div id="main">
        <header>
            <div id="header-container">
                <div class="col-3"><span>truYum</span></div>
                <div class="col-3"></div>
                <div class="col-3"> <a class="menu" href="www.google.com">Menu</a></div>
            </div>
        </header>
        
        <div id="menu">
            <div id="menu-item">Edit Menu Item</div>
            <form:form modelAttribute="menuItem" action="/edit-menu-item" method="post">
            <table>
               <tr>
                   <td>
                       <form:label path="name">Name</form:label>
                    </td>
               </tr>
                <tr>
                    <td>
                        <form:input type="text" id="name-textbox" path="name"/>
                        <form:errors path="name"  id="fontcolor" cssClass="text-warning"></form:errors>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <!--<label for="price">Price</label>-->
                       <form:label path="price">Price</form:label> 
                     </td>
                     
                     <td>
                        <!--<label for="inStock">Active</label><br>-->
                       <form:label path="active">Active</form:label>
                     </td>

                     <td >
                        <form:label path="dateOfLaunch">DateOfLaunch</form:label><br>
                     </td>

                     <td>
                        <form:label path="category">Category</form:label>
                     </td>
                </tr>

                <tr>
                    <td>
                       <form:input type="text" path="price" />
                    </td>

                    <td>
                    	<c:if test="${menuItem.active eq true }">
                    	<form:radiobutton path="active" value="true"/>Yes
                    	<form:radiobutton path="active"/>No								 																					
                    	</c:if>
                    	<c:if test="${menuItem.active eq false }">
                    	<form:radiobutton path="active" />Yes
                        <form:radiobutton path="active" value="false"/>No
                        </c:if>
                    </td>

                    <td>
                        <form:input path="dateOfLaunch"></form:input>
                         <form:errors path="dateOfLaunch"  id="fontcolor" cssClass="text-warning"></form:errors>
                    </td>

                    <td>
                        <form:select path="category" value="${ menuItem.category }">
                            <option>Starters</option>
                            <option>Main Course</option>
                            <option>Desert</option>
                            <option>Drinks</option>
                        </form:select>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form:checkbox path="freeDelivery" />Free Delivery
                    </td>
                </tr>

                <tr>
                    <td>
                        <button class="btn" type="submit">Save</button>
                    </td>
                </tr>
            </table>

            </form:form>
        </div>
        
    </div>
    <footer>Copyright &copy; 2019</footer>
</body>
</html>