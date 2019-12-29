<%-- 
    Document   : Update
    Created on : Nov 24, 2019, 4:27:36 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ADMIN}
        </font>
        <br>
        <a href="logout">Logout</a>
        <h1>Update Book</h1>
        <form action="updateBook">
            <c:set var="error" value="${requestScope.UPDATEERRORS}"/>
            Title <input type="text" name="txtTitle" value="${param.txtTitle}" /> 
            <c:if test="${not empty error.title}">
                <font color="red">
                ${error.title} 
                </font>
            </c:if>
            <br>
            Price <input type="text" name="txtPrice" value="${param.txtPrice}" /> 
            <c:if test="${not empty error.price}">
                <font color="red">
                ${error.price} 
                </font>
            </c:if>
            <br>
            Author <input type="text" name="txtAuthor" value="${param.txtAuthor}" /> 
            <c:if test="${not empty error.author}">
                <font color="red">
                ${error.author} 
                </font>
            </c:if>
            <br>
            Category  <select name="categoryName">
                <c:set var="listCategory" value="${sessionScope.LISTCATEGORY}"/>
                <c:forEach var="category" items="${listCategory}">
                    <c:choose>
                        <c:when test="${param.categoryName == category.categoryName}">
                            <option selected value="${category.categoryName}">${category.categoryName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.categoryName}">${category.categoryName}</option>
                        </c:otherwise>
                    </c:choose>


                </c:forEach>

            </select>
                <br>
            
            Import Date<input type="text" name="txtImportDate" value="${param.txtImportDate}" /> 
            <c:if test="${not empty error.importDate}">
                <font color="red">
                ${error.importDate} 
                </font>
            </c:if>
            <br>
            Quantity <input type="text" name="txtQuantity" value="${param.txtQuantity}" /> 
            <c:if test="${not empty error.quantity}">
                <font color="red">
                ${error.quantity} 
                </font>
            </c:if>
            <br>
            <input type="hidden" name="txtBookId" value="${param.txtBookId}" />
            <input type="submit" value="Update" name="btAction"/>
            <input type="submit" value="Cancel" name="btAction"/>
        </form>
    </body>
</html>
